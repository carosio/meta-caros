DESCRIPTION = "Revised OpenFlow Library (ROFL)"
SECTION = "net"
LICENSE = "EICT"
LICENSE_FLAGS = "commercial"
LIC_FILES_CHKSUM = "file://COPYING;md5=27248591e3b23405cad3583a30f600cd"

SRCREV="AUTOINC"
PR = "r0"

SRC_URI = "git://git@git.tpip.net/rofl.git;protocol=ssh \
	   file://add_autotools_support.patch;apply=yes"

S = "${WORKDIR}/git"

inherit autotools

PACKAGES =+ "lib${PN}-dev lib${PN}-staticdev lib${PN} ${PN}-adpd ${PN}-ethctld ${PN}-ipctld ${PN}-ipadaptd"
RDEPENDS_${PN} += "${PN}-adpd ${PN}-ethctld ${PN}-ipctld ${PN}-ipadaptd"

FILES_lib${PN} = "${libdir}/*.so.*"
FILES_lib${PN}-dev = "${libdir}/*.so ${libdir}/*.la"
FILES_lib${PN}-staticdev = "${libdir}/*.a"
FILES_${PN}-adpd = "${sbindir}/adpd"
FILES_${PN}-ethctld = "${sbindir}/ethctld"
FILES_${PN}-ipctld = "${sbindir}/ipctld"
FILES_${PN}-ipadaptd = "${sbindir}/ipadaptd"