DESCRIPTION = "JSON-RPC API toolkit"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/hello.erl;beginline=1;endline=19;md5=8af7faa07f2606ede8e44010750a0093"

PR = "r1"

SRC_URI = "https://github.com/travelping/hello/archive/${PV}.tar.gz;downloadfilename=erlang-hello-${PV}.tar.gz"
SRC_URI[md5sum] = "0e1a20a36729bcfd5da20ce3d3db360c"
SRC_URI[sha256sum] = "ba2e87ddc821c7bf8ce7eaec2d8766a4638037f6d5e7fbd45bd49467ac66932c"

S = "${WORKDIR}/hello-${PV}"

TETRAPAK_OPTS += "-o build.version ${PV}"

inherit tetrapak

DEPENDS += "erlang-cowboy erlang-erlzmq erlang-ex-uri erlang-ibrowse erlang-tetrapak-yang"
RDEPENDS_${PN} += "erlang-cowboy erlang-erlzmq erlang-ex-uri erlang-ibrowse erlang-yang"

RCONFLICTS_${PN} = "erlang-hello2"
RREPLACES_${PN} = "erlang-hello2"

python () {
    erlang_def_package("hello", "hello-*", "ebin priv", "src include README.md examples NEWS.md", d)
}
