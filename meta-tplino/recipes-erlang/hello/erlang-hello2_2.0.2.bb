DESCRIPTION = "JSON-RPC API toolkit"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/hello2.erl;beginline=1;endline=19;md5=8af7faa07f2606ede8e44010750a0093"

SRCREV = "c1c21a95580d74443fb1a5383269faf491256a88"
PR = "r1"

SRC_URI = "git://git@git.tpip.net/hello2.git;protocol=ssh"

S = "${WORKDIR}/git"

inherit tetrapak

DEPENDS = " erlang-cowboy erlang-yang-native erlang-erlzmq erlang-ex-uri erlang-ibrowse "
RDEPENDS_${PN} = " erlang-cowboy erlang-yang erlang-erlzmq erlang-ex-uri erlang-ibrowse "

python () {
    erlang_def_package("hello2", "hello2-*", "ebin priv", "src include README.md examples", d)
}
