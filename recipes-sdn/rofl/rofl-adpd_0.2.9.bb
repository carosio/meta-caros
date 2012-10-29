DESCRIPTION = "Revised OpenFlow Library ADPD (ROFL)"
SECTION = "net"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=5d425c8f3157dbf212db2ec53d9e5132"

DEPENDS = "rofl-core"
RDEPENDS = "busybox-runit"

PR = "r1"

SRC_URI = "git://gitolite@codebasin.net/adpd.git;protocol=ssh;tag=v${PV} \
	   file://run"

S = "${WORKDIR}/git"

inherit autotools

INITSCRIPT_NAME = "adpd"
INITSCRIPT_PARAMS = "defaults 51"

EXTRA_OECONF += "--enable-debug --enable-backwards_compat"
CXXFLAGS_append = " -I${STAGING_INCDIR}/rofl"

FILES_${PN} += '/usr/lib/libofhal_x86.so'

do_install_append() {
    install -d ${D}${sysconfdir} \
	${D}${sysconfdir}/rofl \
	${D}/var/service/adpd

    install -m0755 ${WORKDIR}/run ${D}/var/service/adpd
}
