DESCRIPTION = "BigCouch DB"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=08c8f8678f4dae8c7e726dbd67938f8d"

# This is a version for the current unstable HEAD release (planned to become couchdb 2.0.0)
# pinned to a specific and (somewhat) tested git revision (incl. all deps)
#
# regenerate the SRCREV's from a tree build with the _git recipe:
#   for i in *; do (cd $i; v=`git rev-parse HEAD`; i=`echo -n $i | tr 'a-z' 'A-Z'`; echo "SRCREV_$i = \"$v\"" ); done

PR = "r0.3"

SRC_URI = "git://github.com/apache/couchdb.git;protocol=git \
	   file://reltool_config_fixup.patch;apply=yes \
	   file://enable_erlang_views.patch;apply=yes \
           file://bigcouch.service"

SRCREV = "50c0cf4ed005543e4f2b301dba496c784ec2059d"
SRCREV_CHTTPD = "1ee2725cfaf1a52a2d0ba8c4f68883a9f78a625f"
SRCREV_CONFIG = "31d14bc39b1b152a4193ea67c52360db09151b10"
SRCREV_COUCH = "8675de9fc3f50e4804c62113dd90689bbea1c224"
SRCREV_COUCH_DBUPDATES = "ae37404d0f8e757837ca0e83e1aa69e8d7fbe11e"
SRCREV_COUCH_INDEX = "ef483cbccbb39feffefe112832d15fba3090dbfb"
SRCREV_COUCH_LOG = "cecac37b316650afc0dbfb7778c838111c636f51"
SRCREV_COUCH_MRVIEW = "60eb161f487125dc1e006b01975fa9b406e203e1"
SRCREV_COUCH_PLUGINS = "4bb066a8d134e257fd78d4dc29d2cdfdd1640804"
SRCREV_COUCH_REPLICATOR = "edd5dc40adf99b643096e6c68816631a1f58dfc8"
SRCREV_DDOC_CACHE = "f17c21fb635f86319cce46fd502e01efebea59f8"
SRCREV_DOCS = "fe7f7bf2ad400c417b3b64b03e438cfb96beefa1"
SRCREV_ETS_LRU = "aa817bd22eb0ecc95180a1f386856a2e7da4b12f"
SRCREV_FABRIC = "58380bb879f7b07d0a880ff1a5ddde920f0e52f6"
SRCREV_FAUXTON = "8cb432c5f35022cc77cc115c78cffde1a5e1cc7c"
SRCREV_GOLDRUSH = "71e63212f12c25827e0c1b4198d37d5d018a7fec"
SRCREV_IBROWSE = "4af2d408607874d124414ac45df1edbe3961d1cd"
SRCREV_JIFFY = "eca4f70449d7ba91468a333ac75fa10755bcf3b8"
SRCREV_LAGER = "0df1dd4949133947841f2982420f264c741bc612"
SRCREV_MECK = "dde759050eff19a1a80fd854d7375174b191665d"
SRCREV_MEM3 = "9dbea0340b54c234a15ee259ae69dea29fafc927"
SRCREV_MOCHIWEB = "15dc622fd8fd7c59890bc7d057f332e0aea46a77"
SRCREV_OAUTH = "2e26bf0f4ace399ea0dcc593255ec042445c02e9"
SRCREV_REXI = "6cd3b812c0bb5c956d8264aae0ffdd94bbd52314"
SRCREV_SNAPPY = "af4c2be3fe855845613cf46914ac1fa63a5b3af2"

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
                    ${BIGCOUCH_PREFIX}/lib/couch-*/priv/.debug \
                    ${BIGCOUCH_PREFIX}/lib/jiffy-*/priv/.debug \
                    ${BIGCOUCH_PREFIX}/lib/snappy-*/priv/.debug"

SYSTEMD_SERVICE_${PN} = "bigcouch.service"

do_configure() {
    # pin all deps
    sed -i \
	-e"/\"couchdb-couch-log\"/ s|{branch[^}]*}|\"${SRCREV_COUCH_LOG}\"|g" \
	-e"/\"couchdb-config\"/ s|{branch[^}]*}|\"${SRCREV_CONFIG}\"|g" \
	-e"/\"couchdb-chttpd\"/ s|{branch[^}]*}|\"${SRCREV_CHTTPD}\"|g" \
	-e"/\"couchdb-couch\"/ s|{branch[^}]*}|\"${SRCREV_COUCH}\"|g" \
	-e"/\"couchdb-couch-index\"/ s|{branch[^}]*}|\"${SRCREV_COUCH_INDEX}\"|g" \
	-e"/\"couchdb-couch-mrview\"/ s|{branch[^}]*}|\"${SRCREV_COUCH_MRVIEW}\"|g" \
	-e"/\"couchdb-couch-replicator\"/ s|{branch[^}]*}|\"${SRCREV_COUCH_REPLICATOR}\"|g" \
	-e"/\"couchdb-couch-dbupdates\"/ s|{branch[^}]*}|\"${SRCREV_COUCH_DBUPDATES}\"|g" \
	-e"/\"couchdb-couch-plugins\"/ s|{branch[^}]*}|\"${SRCREV_COUCH_PLUGINS}\"|g" \
	-e"/\"couchdb-ddoc-cache\"/ s|{branch[^}]*}|\"${SRCREV_DDOC_CACHE}\"|g" \
	-e"/\"couchdb-ets-lru\"/ s|{branch[^}]*}|\"${SRCREV_ETS_LRU}\"|g" \
	-e"/\"couchdb-fabric\"/ s|{branch[^}]*}|\"${SRCREV_FABRIC}\"|g" \
	-e"/\"couchdb-ibrowse\"/ s|{tag[^}]*}|\"${SRCREV_IBROWSE}\"|g" \
	-e"/\"couchdb-jiffy\"/ s|{branch[^}]*}|\"${SRCREV_JIFFY}\"|g" \
	-e"/\"couchdb-mem3\"/ s|{branch[^}]*}|\"${SRCREV_MEM3}\"|g" \
	-e"/\"couchdb-mochiweb\"/ s|{branch[^}]*}|\"${SRCREV_MOCHIWEB}\"|g" \
	-e"/\"couchdb-oauth\"/ s|{branch[^}]*}|\"${SRCREV_OAUTH}\"|g" \
	-e"/\"couchdb-rexi\"/ s|{branch[^}]*}|\"${SRCREV_REXI}\"|g" \
	-e"/\"couchdb-snappy\"/ s|{branch[^}]*}|\"${SRCREV_SNAPPY}\"|g" \
	-e"/\"couchdb-fauxton\"/ s|{branch[^}]*}|\"${SRCREV_FAUXTON}\"|g" \
	-e"/\"couchdb-documentation\"/ s|{branch[^}]*}|\"${SRCREV_DOCS}\"|g" rebar.config.script

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
