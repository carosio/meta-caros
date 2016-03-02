DESCRIPTION = "Recipe for erlang-yang"
SECTION = "net"
LICENSE = "CLOSED"
PR = "r1"

SRC_URI = "git://github.com/travelping/yang.git"
SRCREV = "922128924e7e62115ea7b36d8064b524e27e6615"

S = "${WORKDIR}/git"

inherit rebar
