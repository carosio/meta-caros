DESCRIPTION = "a set of statistics functions for erlang"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8f7bb094c7232b058c7e9f2e431f389c"

SRCREV = "3fd09d1b7bbd9de5b2d29f46df04a93fca9ce85e"
PR = "r1"
PV-orig := "${PV}" 
PV = "${PV-orig}-${PR}-${SRCPV}" 

SRC_URI = "git://github.com/boundary/bear.git;protocol=git \
	   file://add-tetrapak.patch;apply=yes"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("bear", "bear-*", "ebin", "src LICENSE README.md", d)
}
