DESCRIPTION = "Iperf is a tool to measure maximum TCP bandwidth, allowing the tuning of various parameters and UDP characteristics"
HOMEPAGE = "http://dast.nlanr.net/Projects/Iperf/"
SECTION = "console/network"
LICENSE = "NewBSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=e8478eae9f479e39bc34975193360298"

PR = "2"

SRC_URI = " \
	${SOURCEFORGE_MIRROR}/iperf/iperf-${PV}.tar.gz \
        file://001-cast-to-max_size_t-instead-of-int.patch \
        file://002-typo-recieve.patch \
        file://003-fix-hyphen-used-as-minus-sign.patch \
        file://005-iperf-die-on-bind-fail.patch \
        file://006-bidirectional-tcp-server.patch \
        file://010-fix-format-security-ftbfs.patch \
        file://011-ipv6_mcast_check.patch \
        file://iperf-tcp.service \
        file://iperf-udp.service \
	"


SRC_URI[md5sum] = "44b5536b67719f4250faed632a3cd016"
SRC_URI[sha256sum] = "636b4eff0431cea80667ea85a67ce4c68698760a9837e1e9d13096d20362265b"

inherit autotools systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "iperf-tcp.service iperf-udp.service"
SYSTEMD_AUTO_ENABLE = "disable"

S="${WORKDIR}/iperf-${PV}"

EXTRA_OECONF = "--exec-prefix=${STAGING_DIR_HOST}${layout_exec_prefix}"

do_configure() {
    export ac_cv_func_malloc_0_nonnull=yes
    gnu-configize --force ${S}
    oe_runconf
}

do_install_append() {
    install -d ${D}${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/*.service ${D}${systemd_unitdir}/system
}


