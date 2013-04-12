DESCRIPTION = "Couch APP"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

SRCREV="7ff8b04534141be28bcb4451044f2e021c6255b3"
PV = "0.1+git${SRCPV}"
PR = "r1"

SRC_URI = "git://github.com/travelping/couchapp-erlang.git;protocol=git"

DEPENDS_append = " erlang-couchbeam "
RDEPENDS_${PN}_append = " erlang-couchbeam "

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("couchapp", "couchapp-*", "ebin priv", "src include", d)
}
