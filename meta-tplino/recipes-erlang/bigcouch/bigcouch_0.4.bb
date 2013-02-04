DESCRIPTION = "BigCouch DB"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ff253ad767462c46be284da12dda33e8"

SRCREV = "42a471caa33c1fa753cf9e9221d678fc0d0f714f"
PR = "r4"

SRC_URI = "git://github.com/cloudant/bigcouch.git;protocol=git;branch=0.4.x \
	   file://fix_scons_cross_compilation_brain_fuck.patch;apply=yes \
	   file://reltool_config_fixup.patch;apply=yes \
	   file://use_sh_instead_of_bash.patch;apply=yes \
	   file://log-to-var-log.patch;apply=yes \
	   file://enable_erlang_views.patch;apply=yes \
           file://bigcouch.init"

BIGCOUCH_PREFIX ?= "/opt/bigcouch"
BIGCOUCH_DATA ?= "${BIGCOUCH_PREFIX}/var/lib"
BIGCOUCH_VIEW ?= "${BIGCOUCH_PREFIX}/var/lib"
BIGCOUCH_USER ?= "bigcouch"
BIGCOUCH_GROUP ?= "bigcouch"
BIGCOUCH_COOKIE ?= "yocto"

S = "${WORKDIR}/git"

DEPENDS += "erlang-native erlang nspr spidermonkey curl icu"
RDEPENDS_${PN} += "tzdata"

inherit useradd python-dir update-rc.d rebar

INITSCRIPT_NAME = "bigcouch"
INITSCRIPT_PARAMS = "start 90 2 3 4 5 . stop 60 0 1 6 ."

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "--system ${BIGCOUCH_GROUP}"
USERADD_PARAM_${PN} = "--system --home ${BIGCOUCH_PREFIX} \
                       --shell /bin/false -g ${BIGCOUCH_GROUP} \
                       --no-create-home ${BIGCOUCH_USER}"

FILES_${PN} += "${BIGCOUCH_PREFIX} \
                /var/log/bigcouch"

FILES_${PN}-dbg += "${BIGCOUCH_PREFIX}/bin/.debug \
                    ${BIGCOUCH_PREFIX}/erts-*/bin/.debug \
                    ${BIGCOUCH_PREFIX}/lib/*/priv/lib/.debug \
                    ${BIGCOUCH_PREFIX}/lib/couch-*/priv/.debug"

do_rebar_fetch() {
    sed -i -e"s|https://|git://|g" rebar.config
    echo "==> first run"
    ./rebar get-deps || {
	find . -name "rebar.config" | xargs sed -i -e"s|https://|git://|g"
	echo "==> second run"
	./rebar get-deps
    }
}

do_configure() {
    echo "==> configuring bigcouch in rel/bigcouch.config"
    cat > rel/bigcouch.config <<EOF
{prefix, "${BIGCOUCH_PREFIX}"}.
{data_dir, "${BIGCOUCH_DATA}"}.
{view_dir, "${BIGCOUCH_VIEW}"}.
{user, "${BIGCOUCH_USER}"}.
{node_name, "-name bigcouch"}.
{cookie, "-setcookie ${BIGCOUCH_COOKIE}"}.
{cluster_port, 5984}.
{cluster_ssl, 6984}.
{backend_port, 5986}.
EOF

    mkdir -p apps/couch/ebin
}

#CC="${CC}" LD="${LD}" 

do_compile() {
    rm -rf rel/bigcouch
    (cd couchjs; CROSS_COMPILE=${TARGET_PREFIX} CC="${CXX}" scons/scons.py || bbfatal "scons build execution failed.")
    HOST="${TARGET_SYS}" ./rebar root_dir=${STAGING_DIR_HOST}/usr/lib/erlang compile
}

do_install() {
    sed -i s/%VSN%/`git describe --match 1.*`/ apps/couch/ebin/couch.app

    HOST="${TARGET_SYS}" ./rebar root_dir=${STAGING_DIR_HOST}/usr/lib/erlang generate

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/bigcouch.init ${D}${sysconfdir}/init.d/bigcouch

    install -d ${D}/${BIGCOUCH_PREFIX}
    cp -a rel/bigcouch/* ${D}/${BIGCOUCH_PREFIX}
    install -d ${D}/${BIGCOUCH_DATA}
    chown ${BIGCOUCH_USER} ${D}/${BIGCOUCH_DATA}
    install -d ${D}/${BIGCOUCH_VIEW}
    chown ${BIGCOUCH_USER} ${D}/${BIGCOUCH_VIEW}

    install -d ${D}/var/log/bigcouch
}

addtask rebar_fetch after do_unpack before do_patch
