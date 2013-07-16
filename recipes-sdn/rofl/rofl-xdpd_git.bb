DESCRIPTION = "Revised OpenFlow Library XDPD (ROFL)"
SECTION = "net"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=5d425c8f3157dbf212db2ec53d9e5132"

DEPENDS = "rofl-core"

SRCREV="AUTOINC"

PV = "0.1+git${SRCPV}"
PR = "r2"

SRC_URI = " \
    git://git@git.tpip.net/xdpd.git;protocol=ssh;branch=devel-new-mmap \
    file://xdpd.service \
    file://cli.cfg \
"

FILES_${PN} += " \
    ${systemd_unitdir}/system/xdpd.service \
    ${sysconfdir}/xdpd/cli.cfg \
"

CONFFILES_${PN} += " \
    ${sysconfdir}/xdpd/cli.cfg \
"

S = "${WORKDIR}/git"

inherit autotools systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "xdpd.service"

EXTRA_OECONF += "--enable-gnu-linux \
	         ${@base_contains("IMAGE_FEATURES", 'debug-tweaks', '--enable-debug', '', d)}"
PARALLEL_MAKE = ""

CXXFLAGS_append = " -I${STAGING_INCDIR}/rofl"

do_fix_modules () {
	rm -rf ${S}/src/xdpd/fwd-modules/bcm ${S}/src/xdpd/fwd-modules/gnu_linux_dpdk ${S}/src/xdpd/fwd-modules/octeon5650
}

do_patch_append() {
    bb.build.exec_func('do_fix_modules', d)
}

do_install_append() {
    install -d ${D}${sysconfdir}/xdpd/ \
               ${D}${systemd_unitdir}/system/

    install -m 0744 ${WORKDIR}/cli.cfg ${D}${sysconfdir}/xdpd/
    install -m 0644 ${WORKDIR}/xdpd.service ${D}${systemd_unitdir}/system/
}
