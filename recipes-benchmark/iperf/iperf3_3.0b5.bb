DESCRIPTION = "Iperf is a tool to measure maximum TCP bandwidth, allowing the tuning of various parameters and UDP characteristics, Iperf3 is a rewrite from scratch of iperf"
HOMEPAGE = "https://code.google.com/p/iperf/"
SECTION = "console/network"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f2462e4ee9e0f5c6063cf1ad94f64c66"

SRC_URI = "https://iperf.googlecode.com/files/iperf-${PV}.tar.gz"
SRC_URI[md5sum] = "ec298b52840062db19f5912dfc7dce52"
SRC_URI[sha1sum] = "cad3c9390814a53ab718710e9454513f8215113a"
SRC_URI[sha256sum] = "2addc6d49fcdbc8bdc556cc43e23f8fd645dc27416f712769ba99b8b084cbf4d"

inherit autotools

S="${WORKDIR}/iperf-${PV}"

EXTRA_OECONF = "--exec-prefix=${STAGING_DIR_HOST}${layout_exec_prefix}"

do_configure() {
	export ac_cv_func_malloc_0_nonnull=yes
	gnu-configize
	oe_runconf
}

#do_compile() {
#	cd ${WORKDIR}/iperf-${PV}
#	oe_runmake
#}

#do_install() {
#	cd ${WORKDIR}/iperf-${PV}/src
#	oe_runmake DESTDIR=${D} install
#}


