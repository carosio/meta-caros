SUMMARY = "WebSocket emulation - Erlang server http://sockjs.org"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE-MIT-SockJS;md5=8dadf7be2665a431cf0828ff8506e5c4"

SRC_URI = "git://github.com/travelping/sockjs-erlang.git"
SRCREV="${AUTOREV}"
PV = "git${SRCPV}"
PR = "r1"

DEFAULT_PREFERENCE = "-1"

S = "${WORKDIR}/git"

inherit tetrapak

DEPENDS = "erlang-cowboy erlang-pmod-transform"

RDEPENDS_${PN} = "erlang-cowboy"

python () {
    erlang_def_package("sockjs", "sockjs*", "ebin priv", "examples src include test usr", d)
}
