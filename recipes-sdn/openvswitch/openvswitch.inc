SUMMARY = "OpenvSwitch"
DESCRIPTION = "Open vSwitch is a production quality, multilayer virtual switch licensed under the open source Apache 2.0 license. It is designed to enable massive network automation through programmatic extension, while still supporting standard management interfaces and protocols (e.g. NetFlow, sFlow, SPAN, RSPAN, CLI, LACP, 802.1ag)"
HOMEPAGE = "http://openvswitch.org/"
SECTION = "networking"
LICENSE = "Apache-2"

DEPENDS += "openssl python perl systemd"

RDEPENDS_${PN} += "util-linux-uuidgen util-linux-libuuid \
	       python ${PN}-controller"
RDEPENDS_${PN}-controller = "${PN} lsb ${PN}-pki"
RDEPENDS_${PN}-tools = "${PN} perl perl-module-strict"
RDEPENDS_${PN}-pki = "${PN}"
RRECOMMENDS_${PN} += "kernel-module-${PN}"

SRC_URI += "file://openvswitch.service"

EXTRA_OECONF = "--with-linux=${STAGING_KERNEL_DIR} KARCH=${TARGET_ARCH}"

ALLOW_EMPTY_${PN}-pki = "1"
PACKAGES =+ "${PN}-controller ${PN}-tools ${PN}-bugtool ${PN}-pki"

FILES_${PN}-controller = "\
	${sysconfdir}/openvswitch-controller \
	${bindir}/ovs-controller"

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
	${datadir}/openvswitch/python/ovstest \
        "

FILES_${PN}-bugtool = "\
	${sbindir}/ovs-bugtool \
	${datadir}/openvswitch/bugtool-plugins \
	${datadir}/openvswitch/scripts/ovs-bugtool* \
        "

MODULE_PACKAGES = "kernel-module-${PN}"
MODULE_FILES_kernel-module-${PN} = "/lib/modules"

inherit module autotools

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "openvswitch.service"

do_install_append() {
	oe_runmake 'INSTALL_MOD_PATH=${D}' modules_install
	true || rm -fr ${D}/${datadir}/${PN}/pki
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

pkg_postinst_${PN}-controller () {
        # can't do this offline
        if [ "x$D" != "x" ]; then
                exit 1
        fi

	cd $D/${sysconfdir}/openvswitch-controller
        if ! test -e cacert.pem; then
            ln -s $D/${datadir}/${PN}/pki/switchca/cacert.pem cacert.pem
        fi
        if ! test -e privkey.pem || ! test -e cert.pem; then
            oldumask=$(umask)
            umask 077
            ovs-pki req+sign --dir=$D/${datadir}/${PN}/pki tmp controller >/dev/null
            mv tmp-privkey.pem privkey.pem
            mv tmp-cert.pem cert.pem
            mv tmp-req.pem req.pem
            chmod go+r cert.pem req.pem
            umask $oldumask
        fi
}
