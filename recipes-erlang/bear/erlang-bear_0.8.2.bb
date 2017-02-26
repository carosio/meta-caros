SUMMARY = "a set of statistics functions for erlang"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8f7bb094c7232b058c7e9f2e431f389c"

SRCREV = "119234548783af19b8ec75c879c5062676b92571"
PR = "r1"

SRC_URI = "git://github.com/boundary/bear.git;protocol=git"

S = "${WORKDIR}/git"

DEPENDS += "erlang-meck"
RDEPENDS_${PN} += "erlang-meck"

inherit rebar
