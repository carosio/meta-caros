SUMMARY = "Format Dates In Erlang"
SECTION = "devel"
LICENSE = "DWTFYW"
LIC_FILES_CHKSUM = "file://README;md5=131fc5489aad7cc1756b1d8321aa4b97"

PR = "r1"

SRCREV = "473ad8c47379939aeca919547a7d977eaf34c27b"

SRC_URI = "https://github.com/zotonic/dh_date/archive/${SRCREV}.tar.gz;downloadfilename=dh-date-${SRCREV}.tar.gz \
           file://config.ini"
SRC_URI[md5sum] = "a9756c92b5ae14de9d965a6d54b08584"
SRC_URI[sha256sum] = "10098b30679cdd947d738031a8cac2366db4e22072219e5257c630ed83b5ac81"

S = "${WORKDIR}/dh_date-${SRCREV}"

inherit tetrapak

TETRAPAK_OPTS += "-o build.version ${PV}"

addtask do_prepare_compile after do_patch before do_compile

do_prepare_compile() {
    # add tetrapak
    mkdir -p ${S}/tetrapak
    cp ${WORKDIR}/config.ini ${S}/tetrapak
}

python () {
    erlang_def_package("dh-date", "dh_date*", "ebin priv", "src include test", d)
}
