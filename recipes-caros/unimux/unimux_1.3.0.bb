DESCRIPTION = "Universal API Multiplexer"
SECTION = "net"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"

PR = "r1"

inherit caros-app-mix caros-service
include urilock_${PN}_${PV}.inc

APPNAME = "${PN}"
APPVERSION = "${PV}"

CAROS_APP_SERVICE_${PN} = "unimux.service"

REL_NAME = "${APPNAME}"
REL_VSN = "${APPVERSION}"

APP_PREFIX = "/usr/caros-apps"
SYSCONFIG_PREFIX = "${sysconfdir}"

SRC_URI += "git://github.com/carosio/unimux.git;protocol=git"

SRCREV = "bce71ba94356537327871c6e2f87a768bb1bb9a0"

S = "${WORKDIR}/git"
