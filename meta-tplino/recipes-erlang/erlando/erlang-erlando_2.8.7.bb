DESCRIPTION = "Recipe for erlang-erlando"
SECTION = "net"
LICENSE = "CLOSED"
PR = "r1"

SRC_URI = "git://github.com/rabbitmq/erlando.git \
           file://tetrapakize.patch;apply=yes"

SRCREV= "e3f115bd9b14f38518c120138d79adadb90e9872"

S = "${WORKDIR}/git"

TETRAPAK_ERL_LIBS = "${S}"

inherit tetrapak

python () {
    erlang_def_package("erlando", "erlando*", "ebin", "README.md src include test", d)
}
