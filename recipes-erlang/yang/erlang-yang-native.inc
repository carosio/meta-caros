DESCRIPTION = "Recipe for erlang-yang-native"
SECTION = "net"
LICENSE = "CLOSED"
INC_PR = "r1"

SRC_URI = "git://github.com/travelping/yang.git"

S = "${WORKDIR}/git"

inherit tetrapaknative native
