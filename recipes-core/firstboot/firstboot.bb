DESCRIPTION = "Setup script run on first boot"
LICENSE = "CLOSED"
PR = "r1"

SRC_URI = "file://firstboot.sh \
           file://firstboot.service"

FILES_${PN} = "${ROOT_HOME}/firstboot.sh \
               ${systemd_unitdir}/system/firstboot.service"

inherit systemd

SYSTEMD_SERVICE_${PN} = "firstboot.service"

do_install () {
    install -d ${D}${ROOT_HOME}
    install -m 0755  ${WORKDIR}/firstboot.sh ${D}${ROOT_HOME}

    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/firstboot.service ${D}${systemd_unitdir}/system

}
