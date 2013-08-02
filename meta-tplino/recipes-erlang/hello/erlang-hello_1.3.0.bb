DESCRIPTION = "JSON-RPC API toolkit"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/hello.erl;beginline=1;endline=19;md5=8af7faa07f2606ede8e44010750a0093"

SRCREV = "39827053bd7be451c69062e1946c2a18a9f98706"
PR = "r3"
PV-orig := "${PV}" 
PV = "${PV-orig}-${PR}-${SRCPV}" 

SRC_URI = "git://git@git.tpip.net/hello.git;protocol=ssh"

S = "${WORKDIR}/git"

inherit tetrapak

DEPENDS_append = " erlang-cowboy erlang-erlzmq erlang-ex-uri erlang-ibrowse "
RDEPENDS_${PN}_append = " erlang-cowboy erlang-erlzmq erlang-ex-uri erlang-ibrowse "

python () {
    erlang_def_package("hello", "hello-*", "ebin priv", "src include README.md", d)
}
