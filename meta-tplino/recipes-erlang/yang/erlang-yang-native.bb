DESCRIPTION = "Recipe for erlang-yang-native"
SECTION = "net"
LICENSE = "CLOSED"
PR = "r9"

SRC_URI = "git://github.com/travelping/yang.git \
           file://match_nif_load.patch;apply=yes \
           file://tetrapakize.patch;apply=yes"

SRCREV = "f121af9a593d9bd00eec0a25ee60a41a3c12427d"

S = "${WORKDIR}/git"

inherit tetrapaknative native
