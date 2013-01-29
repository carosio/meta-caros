DESCRIPTION = "Revised OpenFlow Library ADPD (ROFL)"
SECTION = "net"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=5d425c8f3157dbf212db2ec53d9e5132"

DEPENDS = "rofl-core"
RDEPENDS = "busybox-runit"

PR = "r0"

SRC_URI = "git://gitolite@codebasin.net/adpd.git;protocol=ssh;tag=v${PV} \
	   file://cfwd_queue_fix.patch \
	   file://run"

S = "${WORKDIR}/git"

inherit autotools

EXTRA_OECONF += "--sysconfdir=/etc/rofl --enable-x86legacy --enable-static --disable-shared --enable-debug"
CXXFLAGS_append = " -I${STAGING_INCDIR}/rofl"

# FILES_${PN} += '/usr/lib/libofhal_x86.so'

do_install_append() {
    install -d ${D}${sysconfdir} \
	${D}${sysconfdir}/rofl \
	${D}/var/service/adpd

    install -m0755 ${WORKDIR}/run ${D}/var/service/adpd
}
