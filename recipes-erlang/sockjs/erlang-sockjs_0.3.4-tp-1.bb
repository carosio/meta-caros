SUMMARY = "WebSocket emulation - Erlang server http://sockjs.org"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE-MIT-SockJS;md5=8dadf7be2665a431cf0828ff8506e5c4"

SRCREV="a843c094481f9c09688650876bc122c4fcb8c13b"
PR = "r0"

SRC_URI = "git://github.com/travelping/sockjs-erlang.git"

S = "${WORKDIR}/git"

inherit tetrapak

DEPENDS = "erlang-cowboy"

RDEPENDS_${PN} = "erlang-cowboy"

python () {
    erlang_def_package("sockjs", "sockjs*", "ebin priv", "examples src include test usr", d)
}
