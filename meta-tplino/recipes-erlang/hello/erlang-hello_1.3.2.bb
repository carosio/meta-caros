DESCRIPTION = "JSON-RPC API toolkit"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/hello.erl;beginline=1;endline=19;md5=8af7faa07f2606ede8e44010750a0093"

SRCREV = "815fff359f22f50f92d81327dd83e301b309afdb"
PR = "r1"

SRC_URI = "git://git@git.tpip.net/hello.git;protocol=ssh"

S = "${WORKDIR}/git"

inherit tetrapak

DEPENDS = "erlang-cowboy erlang-erlzmq erlang-ex-uri erlang-ibrowse"
RDEPENDS_${PN} = "erlang-cowboy erlang-erlzmq erlang-ex-uri erlang-ibrowse"

python () {
    erlang_def_package("hello", "hello-*", "ebin priv", "src include README.md", d)
}
