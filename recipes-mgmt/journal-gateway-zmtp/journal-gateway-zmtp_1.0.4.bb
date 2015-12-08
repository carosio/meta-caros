SUMMARY = "journald gateway for ZMTP based log forwarding"
SECTION = "logging"
DEPENDS += "zeromq"
DEPENDS += "czmq"
DEPENDS += "jansson"
DEPENDS += "systemd"
LICENSE = "LGPL-2.1"
PR = "r2"

LIC_FILES_CHKSUM = "file://LICENSE.LGPL2.1;md5=4fbd65380cdd255951079008b364516c"

inherit autotools caros-service

SRC_URI = "https://github.com/travelping/journal-gateway-zmtp/archive/v${PV}.tar.gz;downloadfilename=${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "44e557a68bce56816ff5d7d482770257"
SRC_URI[sha256sum] = "d1b2030a50252c19ab7fa5594b4aa7430b983f780b0b8193ea80623a08905780"

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

FILES_${PN} += "${bindir}/journal-gateway-zmtp-source ${bindir}/journal-gateway-zmtp-sink ${bindir}/journal-gateway-zmtp-control"

# config files
FILES_${PN} += "${sysconfdir}/journal-gateway-zmtp-sink.conf"
FILES_${PN} += "${sysconfdir}/journal-gateway-zmtp-source.conf"
CONFFILES_${PN} += "${sysconfdir}/journal-gateway-zmtp-sink.conf"
CONFFILES_${PN} += "${sysconfdir}/journal-gateway-zmtp-source.conf"
