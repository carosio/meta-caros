DESCRIPTION = "JSON-RPC API toolkit"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/hello.erl;beginline=1;endline=19;md5=8af7faa07f2606ede8e44010750a0093"

PR = "r2"

SRC_URI = "git://git@git.tpip.net/hello.git;protocol=ssh;tag=${PV}"

S = "${WORKDIR}/git"

inherit tetrapak

DEPENDS += "erlang-cowboy"
DEPENDS += "erlang-erlzmq"
DEPENDS += "erlang-ex-uri"
DEPENDS += "erlang-ibrowse"
DEPENDS += "erlang-lager"
DEPENDS += "erlang-tetrapak-yang"

RDEPENDS_${PN} += "erlang-cowboy"
RDEPENDS_${PN} += "erlang-erlzmq"
RDEPENDS_${PN} += "erlang-ex-uri"
RDEPENDS_${PN} += "erlang-ibrowse"
RDEPENDS_${PN} += "erlang-lager"
RDEPENDS_${PN} += "erlang-yang"

python () {
    erlang_def_package("hello", "hello-*", "ebin priv", "src include README.md examples NEWS.md", d)
}
