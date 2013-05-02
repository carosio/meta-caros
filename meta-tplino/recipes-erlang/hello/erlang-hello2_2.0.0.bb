DESCRIPTION = "JSON-RPC API toolkit"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/hello2.erl;beginline=1;endline=19;md5=8af7faa07f2606ede8e44010750a0093"

SRCREV = "4800ff507fad4f11042de2ce00a716f36f07d29b"
PR = "r0"

SRC_URI = "git://git@git.tpip.net/hello2.git;protocol=ssh"

S = "${WORKDIR}/git"

inherit tetrapak

DEPENDS_append = " erlang-cowboy erlang-yang-native erlang-erlzmq erlang-ex-uri erlang-ibrowse "
RDEPENDS_${PN}_append = " erlang-cowboy erlang-yang erlang-erlzmq erlang-ex-uri erlang-ibrowse "

python () {
    erlang_def_package("hello2", "hello2-*", "ebin priv", "src include README.md", d)
}
