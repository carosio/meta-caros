FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://arp-security.conf"

PR := "${PR}.1"

do_install_append() {
    install -d 0755 "${D}${sysconfdir}/sysctl.d"
    install -m 0644 "${WORKDIR}/arp-security.conf" "${D}${sysconfdir}/sysctl.d/20-arp-security.conf"
}

CONFFILES_${PN} += "${sysconfdir}/sysctl.d/20-arp-security.conf"

