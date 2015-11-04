SUMMARY = "journald gateway for ZMTP based log forwarding"
SECTION = "logging"
DEPENDS += "zeromq"
DEPENDS += "czmq"
DEPENDS += "jansson"
DEPENDS += "systemd"
LICENSE = "LGPL-2.1"
PR = "r1"

LIC_FILES_CHKSUM = "file://LICENSE.LGPL2.1;md5=4fbd65380cdd255951079008b364516c"

inherit autotools systemd

SRC_URI = "https://github.com/travelping/journal-gateway-zmtp/archive/v${PV}.tar.gz;downloadfilename=${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "76d19b039dbf29671f17ac4479f45204"
SRC_URI[sha256sum] = "5297987445fa88e87249b0ca59f7dcdb58ea1a8d612f50b649c5fc387077bac3"

S = "${WORKDIR}/${PN}-${PV}"

SYSTEMD_AUTO_ENABLE = "disable"
SYSTEMD_SERVICE_${PN} = "${PN}-sink.service"
SYSTEMD_SERVICE_${PN} += "${PN}-source.service"
SYSTEMD_SERVICE_${PN} += "rotate_logs.service"
SYSTEMD_SERVICE_${PN} += "rotate_logs.timer"

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
    install -m 0644 ${S}/misc/rotate_logs.service ${D}${systemd_unitdir}/system/.
    install -m 0644 ${S}/misc/rotate_logs.timer ${D}${systemd_unitdir}/system/.

    install -d ${D}${sysconfdir}
    install -m 0644 ${S}/misc/journal-gateway-zmtp-sink.conf ${D}${sysconfdir}/.
    install -m 0644 ${S}/misc/journal-gateway-zmtp-source.conf ${D}${sysconfdir}/.
}

FILES_${PN} = "${bindir}/journal-gateway-zmtp-source ${bindir}/journal-gateway-zmtp-sink ${bindir}/journal-gateway-zmtp-control"

# systemd units
FILES_${PN} += "${systemd_unitdir}/system/journal-gateway-zmtp-sink.service"
FILES_${PN} += "${systemd_unitdir}/system/journal-gateway-zmtp-source.service"
FILES_${PN} += "${systemd_unitdir}/system/rotate_logs.service"
FILES_${PN} += "${systemd_unitdir}/system/rotate_logs.timer"

# config files
FILES_${PN} += "${sysconfdir}/journal-gateway-zmtp-sink.conf"
FILES_${PN} += "${sysconfdir}/journal-gateway-zmtp-source.conf"
CONFFILES_${PN} += "${sysconfdir}/journal-gateway-zmtp-sink.conf"
CONFFILES_${PN} += "${sysconfdir}/journal-gateway-zmtp-source.conf"
