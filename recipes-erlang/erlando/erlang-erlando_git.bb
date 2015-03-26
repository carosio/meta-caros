SUMMARY = "Recipe for erlang-erlando"
SECTION = "net"
LICENSE = "MPL-1.0"
LIC_FILES_CHKSUM = "file://README.md;beginline=617;md5=b5d78b4446f5ee806a90c7416aada096"

SRC_URI = "git://github.com/travelping/erlando.git \
           file://tetrapakize.patch;apply=yes"
SRCREV = "${AUTOREV}"
PV = "git${SRCPV}"
PR = "r0"

DEFAULT_PREFERENCE = "-1"

DEPENDS = "erlang-pmod-transform"

S = "${WORKDIR}/git"

TETRAPAK_ERL_LIBS = "${S}"

inherit tetrapak

python () {
    erlang_def_package("erlando", "erlando*", "ebin", "README.md src include test", d)
}
