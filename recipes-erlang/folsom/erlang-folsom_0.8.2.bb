SUMMARY = "Erlang based metrics system"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8f7bb094c7232b058c7e9f2e431f389c"

PR = "r1"

SRC_URI = "git://github.com/folsom-project/folsom;protocol=git "
SRCREV = "38e2cce7c64ce1f0a3a918d90394cfc0a940b1ba"

DEPENDS_append = " erlang-bear "
RDEPENDS_${PN}_append = " erlang-bear "

S = "${WORKDIR}/git"

inherit rebar
