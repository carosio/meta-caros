DESCRIPTION = "Erlang based metrics system"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8f7bb094c7232b058c7e9f2e431f389c"

SRCREV = "5e2d2e0da4ce9d2edb5d60414ee554ec6cebf464"
PR = "r1"
PV-orig := "${PV}" 
PV = "${PV-orig}-${PR}-${SRCPV}"

SRC_URI = "git://github.com/boundary/folsom.git;protocol=git \
	   file://add-tetrapak.patch;apply=yes"

DEPENDS_append = " erlang-bear "
RDEPENDS_${PN}_append = " erlang-bear "

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("folsom", "folsom-*", "ebin", "include test src LICENSE README.md", d)
}
