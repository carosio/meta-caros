DESCRIPTION = "Universal API Multiplexer"
SECTION = "net"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"

PR = "r1"

inherit mix

APPNAME = "${PN}"
APPVERSION = "${PV}"

REL_NAME = "${APPNAME}"
REL_VSN = "0.2.4"

APP_PREFIX = "/usr/caros-apps"
SYSCONFIG_PREFIX = "${sysconfdir}"

SRC_URI = "git://github.com/carosio/unimux.git;protocol=git"

SRCREV = "92d98b2c055899b7fc7204fee824f68ea5e88859"

S = "${WORKDIR}/git"
