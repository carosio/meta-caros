DESCRIPTION = "JSON-RPC API toolkit"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/hello.erl;beginline=1;endline=19;md5=8af7faa07f2606ede8e44010750a0093"

SRC_URI = "git://git@git.tpip.net/hello.git;protocol=ssh"
SRCREV = "${AUTOREV}"
PV = "git${SRCPV}"
PR = "r2"

DEFAULT_PREFERENCE = "-1"

S = "${WORKDIR}/git"

inherit tetrapak

DEPENDS += "erlang-tetrapak-yang erlang-cowboy erlang-erlzmq erlang-ex-uri erlang-ibrowse"
RDEPENDS_${PN} += "erlang-cowboy erlang-yang erlang-erlzmq erlang-ex-uri erlang-ibrowse"
RCONFLICTS_${PN} = "erlang-hello2"

python () {
    erlang_def_package("hello", "hello-*", "ebin priv", "src include README.md", d)
}
