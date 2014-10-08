DESCRIPTION = "Recipe for erlang-yang-native"
SECTION = "net"
LICENSE = "CLOSED"

SRC_URI = "git://github.com/travelping/yang.git"
SRCREV = "${AUTOREV}"
PV = "git${SRCPV}"
PR = "r0"

DEFAULT_PREFERENCE = "-1"

S = "${WORKDIR}/git"

inherit tetrapaknative native
