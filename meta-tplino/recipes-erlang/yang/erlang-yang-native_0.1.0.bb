DESCRIPTION = "Recipe for erlang-yang-native"
SECTION = "net"
LICENSE = "CLOSED"
PR = "r1"

SRC_URI = "git://github.com/travelping/yang.git \
           file://match_nif_load.patch;apply=yes \
           file://tetrapakize.patch;apply=yes"

SRCREV = "410b7ae4f8f2f70649e1125c0bf1c2c227b4683f"

S = "${WORKDIR}/git"

inherit tetrapaknative native
