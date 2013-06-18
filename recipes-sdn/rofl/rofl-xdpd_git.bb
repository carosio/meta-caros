DESCRIPTION = "Revised OpenFlow Library XDPD (ROFL)"
SECTION = "net"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=5d425c8f3157dbf212db2ec53d9e5132"

DEPENDS = "rofl-core"

SRCREV="AUTOINC"

PV = "0.1+git${SRCPV}"
PR = "r1"

#SRC_URI = "git://gitolite@codebasin.net/xdpd.git;protocol=ssh;branch=devel"
SRC_URI = "git://git@git.tpip.net/xdpd.git;protocol=ssh;branch=devel"

S = "${WORKDIR}/git"

inherit autotools

EXTRA_OECONF += "--enable-debug --enable-gnu-linux"
PARALLEL_MAKE = ""

CXXFLAGS_append = " -I${STAGING_INCDIR}/rofl"
