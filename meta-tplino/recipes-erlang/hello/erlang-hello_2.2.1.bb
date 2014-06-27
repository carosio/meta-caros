DESCRIPTION = "JSON-RPC API toolkit"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/hello2.erl;md5=e6593df4d48d0bffe419732c774a21d0"

SRCREV = "614980966ac34879bfd35aa68d944f7fff778bf8"
PR = "r1"

SRC_URI = "git://git@git.tpip.net/hello2.git;protocol=ssh"

S = "${WORKDIR}/git"

inherit tetrapak

DEPENDS = "erlang-cowboy erlang-yang-native erlang-erlzmq erlang-ex-uri erlang-ibrowse"
RDEPENDS_${PN} = "erlang-cowboy erlang-yang erlang-erlzmq erlang-ex-uri erlang-ibrowse"

python () {
    erlang_def_package("hello2", "hello2-*", "ebin priv", "src include README.md examples NEWS.md", d)
}
