DESCRIPTION = "journald gateway for ZMTP based log forwarding"
SECTION = "network"
DEPENDS += "zeromq"
DEPENDS += "czmq"
DEPENDS += "jansson"
DEPENDS += "systemd"
LICENSE = "LGPL-2.1"
PR = "r4"

LIC_FILES_CHKSUM = "file://LICENSE.LGPL2.1;md5=4fbd65380cdd255951079008b364516c"

inherit autotools systemd

SRC_URI = "https://github.com/travelping/journal-gateway-zmtp/archive/v${PV}.tar.gz;downloadfilename=${PN}_v${PV}.tar.gz"
SRC_URI[md5sum] = "6d1706bd3997648348c5c1e00358ab5c"
SRC_URI[sha256sum] = "e4100db17dc53d896786a423e0f6744225c07a60e56c146a4325b3a25b4cf21a"

S = "${WORKDIR}/${PN}-${PV}"

SYSTEMD_SERVICE_${PN} = "${PN}-sink.service"
SYSTEMD_SERVICE_${PN} += "${PN}-source.service"

do_compile() {
    cd ${S}
    oe_runmake
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/journal-gateway-zmtp-source ${D}${bindir}
    install -m 0755 ${S}/journal-gateway-zmtp-sink ${D}${bindir}

    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${S}/misc/journal-gateway-zmtp-sink.service ${D}${systemd_unitdir}/system/.
    install -m 0644 ${S}/misc/journal-gateway-zmtp-source.service ${D}${systemd_unitdir}/system/.

    install -d ${D}${sysconfdir}
    install -m 0644 ${S}/misc/journal-gateway-zmtp-sink.conf ${D}${sysconfdir}/.
    install -m 0644 ${S}/misc/journal-gateway-zmtp-source.conf ${D}${sysconfdir}/.
}

FILES_${PN} = "${bindir}/journal-gateway-zmtp-source ${bindir}/journal-gateway-zmtp-sink"

# systemd units
FILES_${PN} += "${systemd_unitdir}/system/journal-gateway-zmtp-sink.service"
FILES_${PN} += "${systemd_unitdir}/system/journal-gateway-zmtp-source.service"

# config files
FILES_${PN} += "${sysconfdir}/journal-gateway-zmtp-sink.conf"
FILES_${PN} += "${sysconfdir}/journal-gateway-zmtp-source.conf"
CONFFILES_${PN} += "${sysconfdir}/journal-gateway-zmtp-sink.conf"
CONFFILES_${PN} += "${sysconfdir}/journal-gateway-zmtp-source.conf"
