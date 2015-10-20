FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://xtables-restore.service \
	    file://xtables-persistence"

inherit systemd

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE_${PN} = "xtables-restore.service"

do_install_append() {
    # Install systemd unit to restore rules on boot
    install -d -m 0755 ${D}${sysconfdir}/systemd/system
    install -d -m 0755 ${D}${sbindir}
    install -m 0755 ${WORKDIR}/xtables-restore.service ${D}${sysconfdir}/systemd/system
    install -m 0755 ${WORKDIR}/xtables-persistence ${D}${sbindir}
}
