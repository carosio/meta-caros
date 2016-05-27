DESCRIPTION = "Revised OpenFlow Library Core Libraries (ROFL)"
SECTION = "net"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=5d425c8f3157dbf212db2ec53d9e5132"

DEPENDS = "libconfig"

SRC_URI = "git://github.com/bisdn/rofl-common.git;branch=stable-0.6"
SRCREV="0adb3723fd6cd61686ad2986cffb434ee053a4fe"

PE = "1"
PR = "r1"

S = "${WORKDIR}/git"

inherit autotools

DEPENDS += "openssl"

EXTRA_OECONF = "${@base_contains("IMAGE_FEATURES", 'debug-tweaks-rofl', '--enable-debug', '', d)}"
