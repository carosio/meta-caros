SUMMARY = "Connection tracking userspace tools for Linux"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=8ca43cbc842c2336e835926c2166c28b"
DEPENDS = "libnfnetlink libnetfilter-conntrack libnetfilter-cttimeout \
           libnetfilter-cthelper libnetfilter-queue bison-native"


SRC_URI = " \
    http://www.netfilter.org/projects/conntrack-tools/files/conntrack-tools-${PV}.tar.bz2;name=tar \
    file://conntrack-failover \
    file://init \
    file://conntrackd.service \
    file://environmentfile \
"

SRC_URI[tar.md5sum] = "b1f9d006e7bf000a77395ff7cd3fac16"
SRC_URI[tar.sha256sum] = "e5c423dc077f9ca8767eaa6cf40446943905711c6a8fe27f9cc1977d4d6aa11e"

inherit autotools-brokensep update-rc.d systemd pkgconfig

PACKAGES =+ "conntrackd"

INITSCRIPT_PACKAGES = "conntrackd"
INITSCRIPT_NAME_conntrackd = "conntrackd conntrack-failover"

SYSTEMD_PACKAGES = "conntrackd"
SYSTEMD_SERVICE_conntrackd = "conntrackd.service"
SYSTEMD_AUTO_ENABLE_conntrackd = "disable"

RDEPENDS_conntrackd = "${PN}"
FILES_conntrackd = "${sbindir}/conntrackd \
		    ${sysconfdir}/default/conntrackd \
		    ${sysconfdir}/conntrackd/conntrackd.conf.sample \
		    ${sysconfdir}/init.d/conntrackdt/conntrackd \
		    ${sysconfdir}/init.d/conntrack-failover"
CONFFILES_conntrackd = "${sysconfdir}/default/conntrackd"

do_install_append() {
    install -d ${D}/${sysconfdir}/conntrackd \
    	       ${D}/${sysconfdir}/default \
    	       ${D}/${sysconfdir}/init.d \
	       ${D}${systemd_unitdir}/system

    install -m 0644 doc/sync/ftfw/conntrackd.conf ${D}/${sysconfdir}/conntrackd/conntrackd.conf.sample
    install -m 0755 ${WORKDIR}/conntrack-failover ${D}/${sysconfdir}/init.d/conntrack-failover
    install -m 0755 ${WORKDIR}/init ${D}/${sysconfdir}/init.d/conntrackd

    install -m 0644 ${WORKDIR}/environmentfile ${D}/${sysconfdir}/default/conntrackd
    install -m 0644 ${WORKDIR}/conntrackd.service ${D}${systemd_unitdir}/system

    # Fix hardcoded paths in scripts
    sed -i 's!/usr/sbin/!${sbindir}/!g' ${D}/${sysconfdir}/init.d/conntrack-failover ${D}/${sysconfdir}/init.d/conntrackd
    sed -i 's!/etc/!${sysconfdir}/!g' ${D}/${sysconfdir}/init.d/conntrack-failover ${D}/${sysconfdir}/init.d/conntrackd
    sed -i 's!/var/!${localstatedir}/!g' ${D}/${sysconfdir}/init.d/conntrack-failover ${D}/${sysconfdir}/init.d/conntrackd ${D}/${sysconfdir}/conntrackd/conntrackd.conf.sample
    sed -i 's!^export PATH=.*!export PATH=${base_sbindir}:${base_bindir}:${sbindir}:${bindir}!' ${D}/${sysconfdir}/init.d/conntrackd
}
