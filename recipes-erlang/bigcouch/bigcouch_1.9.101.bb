SUMMARY = "BigCouch DB"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d1d0149ed2d0955d0b2545d326d60d54"

# This is a version for the current unstable HEAD release (planned to become couchdb 2.0.0)
# pinned to a specific and (somewhat) tested git revision (incl. all deps)
#
# regenerate the SRCREV's from a tree build with the _git recipe:
#   for i in *; do (cd $i; v=`git rev-parse HEAD`; i=`echo -n $i | tr 'a-z' 'A-Z'`; echo "SRCREV_$i = \"$v\"" ); done

PR = "r1"

SRC_URI = "git://github.com/apache/couchdb.git;protocol=git \
	   file://reltool_config_fixup.patch;apply=yes \
	   file://enable_erlang_views.patch;apply=yes \
           file://bigcouch.service"

SRCREV = "4ad8de2baefa471c3beb0cacfc67569deb1bad99"
SRCREV_B64URL = "0eabe448afb6d9d6b9f7f175a7f5873aa65c1bbc"
SRCREV_BEAR = "5f998064d178b1b8d01ed90c228d50d8097b12d3"
SRCREV_CASSIM = "f8cd12a8a9e5c7688b480999dcb14d5d22876a24"
SRCREV_CHTTPD = "58020abb2b8551d196d07fb328e2668eef122342"
SRCREV_CONFIG = "31d14bc39b1b152a4193ea67c52360db09151b10"
SRCREV_COUCH = "cb30f6ac7ee7a52337666bc95a8b82cc558ad785"
SRCREV_COUCH_DBUPDATES = "690c8c2cbc948af4c50b1866d7b087c5a78391e6"
SRCREV_COUCH_EVENT = "06477c261205813e732d8f418c70942c33e01a8b"
SRCREV_COUCH_INDEX = "9e638f6848c197e942c8f59eed4d61add5d150d1"
SRCREV_COUCH_LOG = "88b9718f25c91a62de2af462aaa2ceb67d787116"
SRCREV_COUCH_MRVIEW = "33a51ccd6d202f6f16e89eead7810e57f49adfbf"
SRCREV_COUCH_PLUGINS = "ebfbd9df15a82090bdc4fdaa471f6d41a3ca80c4"
SRCREV_COUCH_REPLICATOR = "aafb5f9d9b97d0cadb21b10e338cca6c9caf6afd"
SRCREV_COUCH_STATS= "ebb4f633f43e3b1938abed38a469784c542bde4a"
SRCREV_DDOC_CACHE = "4ffc6b00031a1dea42bd8b549fc32d6918c41396"
SRCREV_DOCUMENTATION = "ff91acf23d77bec0259c370321676e8c6c2e81c1"
SRCREV_ETS_LRU = "e9a75fb72400044a04a36441a920016dce4ff84c"
SRCREV_FABRIC = "a71701c91c7f674ff20017332a4ddbb0dbaf1d11"
SRCREV_FAUXTON = "8de3ed3ceec6134a36c6d82f286edadce5ecb750"
SRCREV_FOLSOM = "fbb7bc83806520ffef84107c85f53c1f7113c20d"
SRCREV_GOLDRUSH = "71e63212f12c25827e0c1b4198d37d5d018a7fec"
SRCREV_GLOBAL_CHANGES = "5c0b30e2e63a22d100bdc4bf532c1df73fbb9b59"
SRCREV_IBROWSE = "4af2d408607874d124414ac45df1edbe3961d1cd"
SRCREV_IOQ = "6293a8b88d1cd67c254ac764a046a1c3a0905be4"
SRCREV_JIFFY = "dc451c9f52092e34ad2b271c39b9d5e967bc12d7"
SRCREV_KHASH = "f87d8bc73221f8e6c94761e9486b4fcbfd395e25"
SRCREV_LAGER = "f18eddf1f487358b262995c870dc882836d11ba0"
SRCREV_MECK = "dde759050eff19a1a80fd854d7375174b191665d"
SRCREV_MEM3 = "64c0c7475ccf2ea9b4f87b6d287850d7b230c089"
SRCREV_MOCHIWEB = "b9d5153842ab4e8bcfeb6dad081d958a61f0f462"
SRCREV_OAUTH = "2e26bf0f4ace399ea0dcc593255ec042445c02e9"
SRCREV_REXI = "bbf59a2174772c76e26daa5afeeb6f6c038088fb"
SRCREV_SNAPPY = "0e8d26d8916af0882fae23042dc6cf80504891a5"

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
    # pin all deps
    sed -i \
	-e"/\"b64url\"/ s|{branch[^}]*}|\"${SRCREV_B64URL}\"|g" \
	-e"/\"bear\"/ s|{branch[^}]*}|\"${SRCREV_BEAR}\"|g" \
	-e"/\"cassim\"/ s|{branch[^}]*}|\"${SRCREV_CASSIM}\"|g" \
	-e"/\"chttpd\"/ s|{branch[^}]*}|\"${SRCREV_CHTTPD}\"|g" \
	-e"/\"config\"/ s|{branch[^}]*}|\"${SRCREV_CONFIG}\"|g" \
	-e"/\"couch\"/ s|{branch[^}]*}|\"${SRCREV_COUCH}\"|g" \
	-e"/\"couch-dbupdates\"/ s|{branch[^}]*}|\"${SRCREV_COUCH_DBUPDATES}\"|g" \
	-e"/\"couch-event\"/ s|{branch[^}]*}|\"${SRCREV_COUCH_EVENT}\"|g" \
	-e"/\"couch-index\"/ s|{branch[^}]*}|\"${SRCREV_COUCH_INDEX}\"|g" \
	-e"/\"couch-log\"/ s|{branch[^}]*}|\"${SRCREV_COUCH_LOG}\"|g" \
	-e"/\"couch-mrview\"/ s|{branch[^}]*}|\"${SRCREV_COUCH_MRVIEW}\"|g" \
	-e"/\"couch-plugins\"/ s|{branch[^}]*}|\"${SRCREV_COUCH_PLUGINS}\"|g" \
	-e"/\"couch-replicator\"/ s|{branch[^}]*}|\"${SRCREV_COUCH_REPLICATOR}\"|g" \
	-e"/\"couch-stats\"/ s|{branch[^}]*}|\"${SRCREV_COUCH_STATS}\"|g" \
	-e"/\"ddoc-cache\"/ s|{branch[^}]*}|\"${SRCREV_DDOC_CACHE}\"|g" \
	-e"/\"documentation\"/ s|{branch[^}]*}|\"${SRCREV_DOCUMENTATION}\"|g" \
	-e"/\"ets-lru\"/ s|{branch[^}]*}|\"${SRCREV_ETS_LRU}\"|g" \
	-e"/\"fabric\"/ s|{branch[^}]*}|\"${SRCREV_FABRIC}\"|g" \
	-e"/\"fauxton\"/ s|{branch[^}]*}|\"${SRCREV_FAUXTON}\"|g" \
	-e"/\"folsom\"/ s|{branch[^}]*}|\"${SRCREV_FOLSOM}\"|g" \
	-e"/\"global-changes\"/ s|{branch[^}]*}|\"${SRCREV_GLOBAL_CHANGES}\"|g" \
	-e"/\"goldrush\"/ s|{branch[^}]*}|\"${SRCREV_GOLDRUSH}\"|g" \
	-e"/\"ibrowse\"/ s|{branch[^}]*}|\"${SRCREV_IBROWSE}\"|g" \
	-e"/\"ioq\"/ s|{branch[^}]*}|\"${SRCREV_IOQ}\"|g" \
	-e"/\"jiffy\"/ s|{branch[^}]*}|\"${SRCREV_JIFFY}\"|g" \
	-e"/\"khash\"/ s|{branch[^}]*}|\"${SRCREV_KHASH}\"|g" \
	-e"/\"lager\"/ s|{branch[^}]*}|\"${SRCREV_LAGER}\"|g" \
	-e"/\"meck\"/ s|{branch[^}]*}|\"${SRCREV_MECK}\"|g" \
	-e"/\"mem3\"/ s|{branch[^}]*}|\"${SRCREV_MEM3}\"|g" \
	-e"/\"mochiweb\"/ s|{branch[^}]*}|\"${SRCREV_MOCHIWEB}\"|g" \
	-e"/\"oauth\"/ s|{branch[^}]*}|\"${SRCREV_OAUTH}\"|g" \
	-e"/\"rexi\"/ s|{branch[^}]*}|\"${SRCREV_REXI}\"|g" \
	-e"/\"snappy\"/ s|{branch[^}]*}|\"${SRCREV_SNAPPY}\"|g" rebar.config.script

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
