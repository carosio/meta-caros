DESCRIPTION = "Universal API Multiplexer"
SECTION = "net"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"

PR = "r1"

inherit mix

APPNAME = "${PN}"
APPVERSION = "${PV}"

REL_NAME = "${APPNAME}"
REL_VSN = "${APPVERSION}"

APP_PREFIX = "/usr/caros-apps"
SYSCONFIG_PREFIX = "${sysconfdir}"

SRC_URI = "git://github.com/carosio/unimux.git;protocol=git"

SRCREV = "34f6f860790cba7b314ec7c638d186007d8ac8e9"

S = "${WORKDIR}/git"
