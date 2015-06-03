SUMMARY = "Recipe for erlang-monads"
SECTION = "net"
LICENSE = "CLOSED"
PR = "r2"

SRC_URI = "git://github.com/carosio/monads.git"
SRCREV= "e5fa78e3ab312048471891d119362b5d2cdbb2b5"

S = "${WORKDIR}/git"

DEPENDS = " erlang-erlando "

inherit tetrapak

python () {
    erlang_def_package("monads", "monads*", "ebin priv", "NEWS.md src include", d)
}
