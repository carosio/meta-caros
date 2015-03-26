SUMMARY = "JSON-RPC API toolkit"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/hello.erl;beginline=1;endline=19;md5=8af7faa07f2606ede8e44010750a0093"

PR = "r2"

SRC_URI = "git://github.com/travelping/hello.git;protocol=git;tag=${PV}"

S = "${WORKDIR}/git"

inherit tetrapak

DEPENDS += "erlang-cowboy"
DEPENDS += "erlang-erlzmq"
DEPENDS += "erlang-ex-uri"
DEPENDS += "erlang-ibrowse"
DEPENDS += "erlang-lager"
DEPENDS += "erlang-tetrapak-yang"

RDEPENDS_${PN} += "erlang-yang"

python () {
    erlang_def_package("hello", "hello-*", "ebin priv", "src include README.md examples NEWS.md", d)
}
