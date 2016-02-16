FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://arp-security.conf \
           file://etc-environ.d-50-locale \
           file://shells"

PR := "${PR}.2"

FILES_${PN} += "50-locale"

do_install_append() {
    install -d 0755 "${D}${sysconfdir}/sysctl.d"
    install -m 0644 "${WORKDIR}/arp-security.conf" "${D}${sysconfdir}/sysctl.d/20-arp-security.conf"
    install -d 0755 "${D}${sysconfdir}/environ.d"
    install -m 0744 "${WORKDIR}/etc-environ.d-50-locale" "${D}${sysconfdir}/environ.d/50-locale"
}

CONFFILES_${PN} += "${sysconfdir}/sysctl.d/20-arp-security.conf \
                    ${sysconfdir}/environ.d/50-locale \
                    "
