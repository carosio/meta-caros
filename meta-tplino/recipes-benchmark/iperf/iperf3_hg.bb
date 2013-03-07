DESCRIPTION = "Iperf is a tool to measure maximum TCP bandwidth, allowing the tuning of various parameters and UDP characteristics, Iperf3 is a rewrite from scratch of iperf"
HOMEPAGE = "https://code.google.com/p/iperf/"
SECTION = "console/network"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f2462e4ee9e0f5c6063cf1ad94f64c66"

SRCREV = "tip"
SRC_URI = "hg://code.google.com/p;module=iperf;rev=${SRCREV};protocol=http"

inherit autotools

#S="${WORKDIR}/iperf-${PV}"
S="${WORKDIR}/iperf"

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


