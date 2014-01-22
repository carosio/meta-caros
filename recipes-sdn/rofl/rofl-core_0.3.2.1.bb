DESCRIPTION = "Revised OpenFlow Library Core Libraries (ROFL)"
SECTION = "net"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=5d425c8f3157dbf212db2ec53d9e5132"

DEPENDS = "libconfig"

SRC_URI = "git://codebasin.net/rofl-core.git;branch=master-0.3"
SRCREV="1017518700198578ac13ac554d405bca17377f11"

PE = "1"
PR = "r1"

S = "${WORKDIR}/git"

inherit autotools

EXTRA_OECONF = "${@base_contains("IMAGE_FEATURES", 'debug-tweaks-rofl', '--enable-debug', '', d)}"
