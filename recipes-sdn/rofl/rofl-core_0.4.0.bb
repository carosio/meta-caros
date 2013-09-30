DESCRIPTION = "Revised OpenFlow Library Core Libraries (ROFL)"
SECTION = "net"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=5d425c8f3157dbf212db2ec53d9e5132"

DEPENDS = "libcli"

SRCREV="ff78de743b22007dc880ea0e70a79200a1beb42c"

PR = "r1"

SRC_URI = "git://git@git.tpip.net/rofl-core.git;protocol=ssh;branch=devel-new-mmap"

S = "${WORKDIR}/git"

inherit autotools

EXTRA_OECONF = "${@base_contains("IMAGE_FEATURES", 'debug-tweaks-rofl', '--enable-debug', '', d)}"
PARALLEL_MAKE = ""
