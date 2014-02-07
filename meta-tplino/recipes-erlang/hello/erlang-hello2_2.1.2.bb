DESCRIPTION = "JSON-RPC API toolkit"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/hello2.erl;md5=e6593df4d48d0bffe419732c774a21d0"

SRCREV = "bcf038f2539ed3dbb8007d0ee9e3e687a7e3de01"
PR = "r1"

SRC_URI = "git://git@git.tpip.net/hello2.git;protocol=ssh"

S = "${WORKDIR}/git"

inherit tetrapak

DEPENDS = "erlang-cowboy erlang-yang-native erlang-erlzmq erlang-ex-uri erlang-ibrowse"
RDEPENDS_${PN} = "erlang-cowboy erlang-yang erlang-erlzmq erlang-ex-uri erlang-ibrowse"

python () {
    erlang_def_package("hello2", "hello2-*", "ebin priv", "src include README.md examples NEWS.md", d)
}
