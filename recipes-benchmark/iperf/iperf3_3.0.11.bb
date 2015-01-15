DESCRIPTION = "Iperf is a tool to measure maximum TCP bandwidth, allowing the tuning of various parameters and UDP characteristics, Iperf3 is a rewrite from scratch of iperf"
HOMEPAGE = "https://code.google.com/p/iperf/"
SECTION = "console/network"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ab59a0c3a4bc3954d1ece68ea19d77a4"

PR = "r1"

SRC_URI = "https://github.com/esnet/${PN}/archive/${PV}.tar.gz \
           file://iperf3.service \
          "

SRC_URI[md5sum] = "c407c25abf2d9134f4e0024497868cc6"
SRC_URI[sha256sum] = "c774b807ea4db20e07558c47951df186b6fb1dd0cdef4282c078853ad87cc712"

inherit autotools-brokensep systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "iperf3.service"

S="${WORKDIR}/iperf-${PV}"

CACHED_CONFIGUREVARS += '\
        ac_cv_func_malloc_0_nonnull=yes'

do_configure() {
    gnu-configize --force ${S}
    oe_runconf
}

do_install_append() {
    install -d ${D}${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/*.service ${D}${systemd_unitdir}/system
}
