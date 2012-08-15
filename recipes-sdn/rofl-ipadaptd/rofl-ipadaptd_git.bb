DESCRIPTION = "Revised OpenFlow Library IPADAPTD (ROFL)"
SECTION = "net"
LICENSE = "commercial"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=e4d7e1de5b6d0c6597a7f5cb61577266"

DEPENDS = "rofl-core"

SRCREV="AUTOINC"
PR = "r0"

SRC_URI = "git://gitolite@codebasin.net/ipadaptd.git;protocol=ssh"

S = "${WORKDIR}/git"

inherit autotools

CXXFLAGS_append = " -I${STAGING_INCDIR}/rofl"


