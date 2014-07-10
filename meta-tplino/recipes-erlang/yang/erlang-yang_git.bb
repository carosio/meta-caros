DESCRIPTION = "Recipe for erlang-yang"
SECTION = "net"
LICENSE = "CLOSED"

SRC_URI = "git://github.com/travelping/yang.git"
SRCREV = "${AUTOREV}"
PV = "git${SRCPV}"
PR = "r0"

DEFAULT_PREFERENCE = "-1"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("yang", "yang*", "ebin priv", "NEWS.md src include c_src patches .pc", d)
}
