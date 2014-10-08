DESCRIPTION = "Recipe for erlang-yang"
SECTION = "net"
LICENSE = "CLOSED"
INC_PR = "r1"

SRC_URI = "git://github.com/travelping/yang.git"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("yang", "yang*", "ebin priv", "NEWS.md src include c_src patches .pc", d)
}
