DESCRIPTION = "BigCouch DB"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d1d0149ed2d0955d0b2545d326d60d54"

SRCREV = "${AUTOREV}"
PV = "git${SRCPV}"
PR = "r2"

DEFAULT_PREFERENCE = "-1"

SRC_URI = "git://github.com/apache/couchdb.git;protocol=git \
	   file://reltool_config_fixup.patch;apply=yes \
	   file://enable_erlang_views.patch;apply=yes \
           file://bigcouch.service"

BIGCOUCH_PREFIX ?= "/opt/bigcouch"
BIGCOUCH_DATA ?= "${BIGCOUCH_PREFIX}/var/lib"
BIGCOUCH_VIEW ?= "${BIGCOUCH_PREFIX}/var/lib"
BIGCOUCH_LOG ?= "${BIGCOUCH_PREFIX}/var/log"
BIGCOUCH_USER ?= "bigcouch"
BIGCOUCH_GROUP ?= "bigcouch"
BIGCOUCH_COOKIE ?= "34598720572589uhuyfgeoyfyf8q70tp783urhi"

S = "${WORKDIR}/git"

DEPENDS += "erlang-native erlang nspr spidermonkey curl icu"
RDEPENDS_${PN} += "tzdata"

inherit useradd python-dir rebar systemd

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "--system ${BIGCOUCH_GROUP}"
USERADD_PARAM_${PN} = "--system --home ${BIGCOUCH_PREFIX} \
                       --shell /bin/false -g ${BIGCOUCH_GROUP} \
                       --no-create-home ${BIGCOUCH_USER}"

FILES_${PN} += "${BIGCOUCH_PREFIX}"

FILES_${PN}-dbg += "${BIGCOUCH_PREFIX}/bin/.debug \
                    ${BIGCOUCH_PREFIX}/erts-*/bin/.debug \
                    ${BIGCOUCH_PREFIX}/lib/*/priv/lib/.debug \
                    ${BIGCOUCH_PREFIX}/lib/*/priv/bin/.debug \
                    ${BIGCOUCH_PREFIX}/lib/b64url-*/priv/.debug \
                    ${BIGCOUCH_PREFIX}/lib/couch-*/priv/.debug \
                    ${BIGCOUCH_PREFIX}/lib/jiffy-*/priv/.debug \
                    ${BIGCOUCH_PREFIX}/lib/khash-*/priv/.debug \
                    ${BIGCOUCH_PREFIX}/lib/snappy-*/priv/.debug"

CONFFILES_${PN} += "${BIGCOUCH_PREFIX}/etc/local.ini ${BIGCOUCH_PREFIX}/etc/vm.args"

SYSTEMD_SERVICE_${PN} = "bigcouch.service"

do_configure() {
    # don't let configure invoke rebar itself
    sed -i -e"s|^rebar|# rebar|g" configure
    ./configure -u "${BIGCOUCH_USER}" -p "${BIGCOUCH_PREFIX}" -d "${BIGCOUCH_DATA}" -v "${BIGCOUCH_VIEW}"
}

do_rebar_deps() {
    rebar get-deps update-deps
}

do_compile() {
    if ! test -f src/couch/rebar.config.script.in; then
	cp src/couch/rebar.config.script src/couch/rebar.config.script.in
    fi
    sed -e"s|/usr|${STAGING_DIR_TARGET}/usr|g" src/couch/rebar.config.script.in > src/couch/rebar.config.script
    HOST="${TARGET_SYS}" rebar root_dir=${STAGING_DIR_HOST}/usr/lib/erlang compile
}

do_install() {
    rm -rf rel/couchdb
    HOST="${TARGET_SYS}" rebar root_dir=${STAGING_DIR_HOST}/usr/lib/erlang generate

    install -d ${D}/${BIGCOUCH_PREFIX} \
	       ${D}/${BIGCOUCH_DATA} \
	       ${D}/${BIGCOUCH_VIEW} \
	       ${D}/${BIGCOUCH_LOG} \
	       ${D}${systemd_unitdir}/system

    cp -a rel/couchdb/* ${D}/${BIGCOUCH_PREFIX}

    chown ${BIGCOUCH_USER} ${D}/${BIGCOUCH_DATA} \
			   ${D}/${BIGCOUCH_VIEW} \
			   ${D}/${BIGCOUCH_LOG}

    install -m 644 ${WORKDIR}/*.service ${D}/${systemd_unitdir}/system
}

addtask rebar_deps after do_configure before do_compile
