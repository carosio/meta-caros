DESCRIPTION = "Revised OpenFlow Library Core Libraries (ROFL)"
SECTION = "net"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=5d425c8f3157dbf212db2ec53d9e5132"

DEPENDS = "libconfig"

SRC_URI = "git://github.com/bisdn/rofl-core.git;branch=master-0.3"
SRCREV="ec1bd289b23f29e5ba4d1b3a549159eac87b1f32"

PE = "1"
PR = "r1"

S = "${WORKDIR}/git"

inherit autotools

EXTRA_OECONF = "${@base_contains("IMAGE_FEATURES", 'debug-tweaks-rofl', '--enable-debug', '', d)}"
