DESCRIPTION = "Revised OpenFlow Library PPPCTLD (ROFL)"
SECTION = "net"
LICENSE = "commercial"
LIC_FILES_CHKSUM = "file://pppctld.cc;beginline=1;endline=3;md5=82d386d3654ce7c9a0e2d7115551d090"

DEPENDS = "rofl-core"

SRCREV="AUTOINC"
PR = "r0"

SRC_URI = "git://gitolite@codebasin.net/rofl-pppctld.git;protocol=ssh \
	   file://add_autotools_support.patch;apply=yes"

S = "${WORKDIR}/git"

inherit autotools

CXXFLAGS_append = " -I${STAGING_INCDIR}/rofl"


