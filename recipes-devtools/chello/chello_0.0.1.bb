SUMMARY = "hello client"
DESCRIPTION = "chello is a small command line tool which can interact with services which expose its api via hello."
SECTION = "net"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"

PR = "r2"

S = "${WORKDIR}/${PN}"

APPVERSION = "${PV}"
DEPENDS += "golang-cross"
RDEPENDS_${PN} += "zeromq"


SRC_URI = "git://github.com/carosio/chello;destsuffix=${PN}"

SRCREV = "8783759616a023a406fe3e9c6320339d29e0f95c"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

do_install() {
	install -m 0755 -d ${D}${bindir}
	install -m 0755 ${S}/chello ${D}${bindir}
}
