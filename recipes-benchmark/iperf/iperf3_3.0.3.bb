DESCRIPTION = "Iperf is a tool to measure maximum TCP bandwidth, allowing the tuning of various parameters and UDP characteristics, Iperf3 is a rewrite from scratch of iperf"
HOMEPAGE = "https://code.google.com/p/iperf/"
SECTION = "console/network"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5f1dfdaa923ec50fc2402d6c90920a7c"

PR = "r1"

SRC_URI = "http://stats.es.net/software/iperf-${PV}.tar.gz \
           file://iperf3.service \
          "

SRC_URI[md5sum] = "dc5f0a7e2b3007ee3203055f000637bb"
SRC_URI[sha256sum] = "79daf3e5e5c933b2fc4843d6d21c98d741fe39b33ac05bd7a11c50d321a2f59d"

inherit autotools systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "iperf3.service"

S="${WORKDIR}/iperf-${PV}"

CACHED_CONFIGUREVARS += '\
        ac_cv_func_malloc_0_nonnull=yes'

do_configure() {
    gnu-configize
    oe_runconf
}

do_install_append() {
    install -d ${D}${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/*.service ${D}${systemd_unitdir}/system
}
