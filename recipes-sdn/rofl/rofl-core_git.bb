DESCRIPTION = "Revised OpenFlow Library Core Libraries (ROFL)"
SECTION = "net"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=5d425c8f3157dbf212db2ec53d9e5132"

DEPENDS = "libcli"

SRCREV="AUTOINC"

PV = "0.3+git${SRCPV}"
PR = "r0"

# SRC_URI = "git://gitolite@codebasin.net/rofl-core.git;protocol=ssh;branch=devel"
SRC_URI = "git://git@git.tpip.net/rofl-core.git;protocol=ssh;branch=devel"

S = "${WORKDIR}/git"

inherit autotools

EXTRA_OECONF = "--enable-debug"
PARALLEL_MAKE = ""
