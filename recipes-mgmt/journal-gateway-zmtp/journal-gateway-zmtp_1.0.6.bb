SUMMARY = "journald gateway for ZMTP based log forwarding"
SECTION = "logging"
DEPENDS += "zeromq"
DEPENDS += "czmq"
DEPENDS += "jansson"
DEPENDS += "systemd"
LICENSE = "LGPL-2.1"
PR = "r1"

LIC_FILES_CHKSUM = "file://LICENSE.LGPL2.1;md5=4fbd65380cdd255951079008b364516c"

inherit autotools caros-service

SRC_URI = "https://github.com/travelping/journal-gateway-zmtp/archive/v${PV}.tar.gz;downloadfilename=${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "08b86b76789e6c53f15c718f9864a77a"
SRC_URI[sha256sum] = "8ab006c663b3d4e4074689f697f6a39e33c05fa636656b94b50baf657281b1cb"

S = "${WORKDIR}/${PN}-${PV}"

SYSTEMD_AUTO_ENABLE = "disable"
SYSTEMD_SERVICE_${PN} = "${PN}-sink.service"
SYSTEMD_SERVICE_${PN} += "${PN}-source.service"
SYSTEMD_SERVICE_${PN} += "journal-sink-logrotate.service"
SYSTEMD_SERVICE_${PN} += "journal-sink-logrotate.timer"

do_compile() {
    echo ${S}
    cd ${S}
    oe_runmake
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/journal-gateway-zmtp-source ${D}${bindir}
    install -m 0755 ${S}/journal-gateway-zmtp-sink ${D}${bindir}
    install -m 0755 ${S}/journal-gateway-zmtp-control ${D}${bindir}

    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${S}/misc/journal-gateway-zmtp-sink.service ${D}${systemd_unitdir}/system/.
    install -m 0644 ${S}/misc/journal-gateway-zmtp-source.service ${D}${systemd_unitdir}/system/.
    install -m 0644 ${S}/misc/rotate_logs.service ${D}${systemd_unitdir}/system/journal-sink-logrotate.service
    sed --in-place -e "s/rotate_logs/journal-sink-logrotate/g" ${D}${systemd_unitdir}/system/journal-sink-logrotate.service
    install -m 0644 ${S}/misc/rotate_logs.timer ${D}${systemd_unitdir}/system/journal-sink-logrotate.timer
    sed --in-place -e "s/rotate_logs/journal-sink-logrotate/g" ${D}${systemd_unitdir}/system/journal-sink-logrotate.timer

    install -d ${D}${sysconfdir}
    install -m 0644 ${S}/misc/journal-gateway-zmtp-sink.conf ${D}${sysconfdir}/.
    install -m 0644 ${S}/misc/journal-gateway-zmtp-source.conf ${D}${sysconfdir}/.
}

FILES_${PN} = "${bindir}/journal-gateway-zmtp-source ${bindir}/journal-gateway-zmtp-sink ${bindir}/journal-gateway-zmtp-control"

# systemd units
FILES_${PN} += "${systemd_unitdir}/system/journal-gateway-zmtp-sink.service"
FILES_${PN} += "${systemd_unitdir}/system/journal-gateway-zmtp-source.service"
FILES_${PN} += "${systemd_unitdir}/system/journal-sink-logrotate.service"
FILES_${PN} += "${systemd_unitdir}/system/journal-sink-logrotate.timer"

# config files
FILES_${PN} += "${sysconfdir}/journal-gateway-zmtp-sink.conf"
FILES_${PN} += "${sysconfdir}/journal-gateway-zmtp-source.conf"
CONFFILES_${PN} += "${sysconfdir}/journal-gateway-zmtp-sink.conf"
CONFFILES_${PN} += "${sysconfdir}/journal-gateway-zmtp-source.conf"
