SUMMARY = "Recipe for erlang-monads"
SECTION = "net"
LICENSE = "CLOSED"
PR = "r1"

SRC_URI = "git://git@git.tpip.net/monads.git;protocol=ssh"
SRCREV= "e5fa78e3ab312048471891d119362b5d2cdbb2b5"

S = "${WORKDIR}/git"

DEPENDS = " erlang-erlando "

inherit tetrapak

python () {
    erlang_def_package("monads", "monads*", "ebin priv", "NEWS.md src include", d)
}
