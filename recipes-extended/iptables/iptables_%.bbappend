
do_install_append() {
    # Install systemd unit to restore rules on boot
    install -d 0755 ${D}${sysconfdir}/systemd/network
    install -d 0755 ${D}/sbin
    install -m 0644 ${WORKDIR}/xtables-restore ${D}${sysconfdir}/systemd/network
    install -m 0644 ${WORKDIR}/xtables-persistence ${D}/sbin
}
