DESCRIPTION = "Revised OpenFlow Library ADPD (ROFL)"
SECTION = "net"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://adpd.cc;beginline=1;endline=3;md5=2ea4b962ed751ec64d301855ef8eda48"

DEPENDS = "rofl-core"

SRCREV="AUTOINC"
PR = "r0"

SRC_URI = "git://gitolite@codebasin.net/rofl-adpd-pub.git;protocol=ssh \
	   file://add_autotools_support.patch;apply=yes"

S = "${WORKDIR}/git"

inherit autotools

CXXFLAGS_append = " -I${STAGING_INCDIR}/rofl"


