DESCRIPTION = "WebSocket emulation - Erlang server http://sockjs.org"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE-MIT-SockJS;md5=8dadf7be2665a431cf0828ff8506e5c4"

SRCREV="4818820cab53f54068ed9e24934371c5c9623355"
PR = "r1"

SRC_URI = "git://github.com/0xAX/sockjs-erlang.git;protocol=git"

S = "${WORKDIR}/git"

inherit tetrapak

DEPENDS = "erlang-cowboy"

RDEPENDS_${PN} = "erlang-cowboy"

python () {
    erlang_def_package("sockjs", "sockjs*", "ebin priv", "examples src include test usr", d)
}
