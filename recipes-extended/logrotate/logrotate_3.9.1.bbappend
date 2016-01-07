FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

inherit systemd

SRC_URI += "file://logrotate.service \
	    file://logrotate.timer"

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "logrotate.service logrotate.timer"


do_install_append() {
    install -d ${D}${systemd_unitdir}/system

    install -m 644 ${WORKDIR}/*.service ${D}${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/*.timer ${D}${systemd_unitdir}/system
}