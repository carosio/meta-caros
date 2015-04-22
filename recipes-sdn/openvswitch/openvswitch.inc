SUMMARY = "OpenvSwitch"
DESCRIPTION = "Open vSwitch is a production quality, multilayer virtual switch licensed under the open source Apache 2.0 license. It is designed to enable massive network automation through programmatic extension, while still supporting standard management interfaces and protocols (e.g. NetFlow, sFlow, SPAN, RSPAN, CLI, LACP, 802.1ag)"
HOMEPAGE = "http://openvswitch.org/"
SECTION = "networking"
LICENSE = "Apache-2"

inherit module autotools systemd

PRINC="6"

DEPENDS += "openssl python"

RDEPENDS_${PN} += "${PN}-vswitchd ${PN}-ovsdb ${PN}-tools ${PN}-pki"
RDEPENDS_${PN}-ovsdb = "${PN}-common"
RDEPENDS_${PN}-vswitchd = "${PN}-common kernel-module-${PN}"
RDEPENDS_${PN}-tools = "${PN}-common ${PN}-python python"
RDEPENDS_kernel-module-${PN} += "kernel-module-vxlan"
RRECOMMENDS_${PN} += "kernel-module-${PN}"

SRC_URI += "file://openvswitch-ovsdb.service \
	    file://openvswitch-vswitchd.service \
	    file://modules.load"

EXTRA_OECONF = "--with-linux=${STAGING_KERNEL_DIR} KARCH=${TARGET_ARCH}"

ALLOW_EMPTY_${PN}-pki = "1"
PACKAGES =+ "${PN}-common ${PN}-ovsdb ${PN}-vswitchd ${PN}-tools ${PN}-bugtool ${PN}-pki ${PN}-python"

FILES_${PN}-common = "\
	/etc/openvswitch \
	${bindir}/ovs-vsctl \
	${bindir}/ovs-appctl \
	"

FILES_${PN}-ovsdb = "\
	${sbindir}/ovsdb-server \
	${bindir}/ovsdb-tool \
	${datadir}/openvswitch/vswitch.ovsschema \
	${datadir}/openvswitch/vtep.ovsschema \
	"

FILES_${PN}-vswitchd  = "\
	${sysconfdir}/modules-load.d/openvswitch.conf \
	${sbindir}/ovs-vswitchd \
	${bindir}/ovs-ofctl \
	${bindir}/ovs-dpctl \
	${bindir}/ovs-dpctl-top \
	${bindir}/vtep-ctl \
	"

FILES_${PN}-pki = "\
	${bindir}/ovs-pki \
	${localstatedir}/lib/openvswitch/pki \
        "

FILES_${PN}-tools = "\
	${bindir}/ovs-benchmark \
	${bindir}/ovs-l3ping \
	${bindir}/ovs-parse-backtrace \
	${bindir}/ovs-parse-leaks \
	${bindir}/ovs-pcap \
	${bindir}/ovs-tcpundump \
	${bindir}/ovs-test \
	${bindir}/ovs-vlan-test \
	${sbindir}/ovs-vlan-bug-workaround \
	${bindir}/ovsdb-client \
	${datadir}/openvswitch/scripts/ovs-lib \
	${datadir}/openvswitch/scripts/ovs-check-dead-ifs \
	${datadir}/openvswitch/scripts/ovs-ctl \
	${datadir}/openvswitch/scripts/ovs-save \
	${datadir}/openvswitch/scripts/ovs-vtep \
        "

FILES_${PN}-bugtool = "\
	${sbindir}/ovs-bugtool \
	${datadir}/openvswitch/bugtool-plugins \
	${datadir}/openvswitch/scripts/ovs-bugtool* \
        "

FILES_${PN}-python = "\
	${datadir}/openvswitch/python \
        "

MODULE_PACKAGES = "kernel-module-${PN}"
MODULE_FILES_kernel-module-${PN} = "/lib/modules"

SYSTEMD_PACKAGES = "${PN} ${PN}-ovsdb ${PN}-vswitchd"
SYSTEMD_SERVICE_${PN}-ovsdb = "openvswitch-ovsdb.service"
SYSTEMD_SERVICE_${PN}-vswitchd = "openvswitch-vswitchd.service"

do_install_append() {
	oe_runmake 'INSTALL_MOD_PATH=${D}' modules_install
	rm -fr ${D}/${datadir}/${PN}/pki ${D}/${localstatedir}
	mkdir -p ${D}/${localstatedir}/lib/openvswitch/pki

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/openvswitch-ovsdb.service ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/openvswitch-vswitchd.service ${D}${systemd_unitdir}/system/

    install -d ${D}${sysconfdir}/modules-load.d
    install -m 0644 ${WORKDIR}/modules.load  ${D}${sysconfdir}/modules-load.d/openvswitch.conf
}

pkg_postinst_${PN}-pki () {
	# can't do this offline
        if [ "x$D" != "x" ]; then
                exit 1
        fi
	if test ! -d $D/${datadir}/${PN}/pki; then
            ovs-pki init --dir=$D/${datadir}/${PN}/pki
        fi
}
