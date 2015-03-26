SUMMARY = "Extended process registry for Erlang."
SECTION = "devel"
LICENSE = "EPL-1.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9567c64d58e18a81951c75d00c10fa98"

PR = "r2"

SRCREV = "fe0eeafb190260c56bc55499523f460132ba407b"
SRC_URI = "git://github.com/travelping/gproc.git;protocol=git"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("gproc", "gproc-*", "ebin", "src LICENSE LICENSE.txt README.md patches test priv include reference rebar rebar.config rebar.config.script Makefile", d)
}
