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
SRC_URI[md5sum] = "e474a75e356083f6c46d51bf2e0cf110"
SRC_URI[sha256sum] = "b14bf90863b7834ea07f7abc25c544957ae9db9eb056eb58d43094759ab2b71f"

S = "${WORKDIR}/${PN}-${PV}"

SYSTEMD_AUTO_ENABLE = "disable"
SYSTEMD_SERVICE_${PN} = "${PN}-sink.service"
SYSTEMD_SERVICE_${PN} += "${PN}-source.service"

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

    install -d ${D}${sysconfdir}
    install -m 0644 ${S}/misc/journal-gateway-zmtp-sink.conf ${D}${sysconfdir}/.
    install -m 0644 ${S}/misc/journal-gateway-zmtp-source.conf ${D}${sysconfdir}/.
}

FILES_${PN} = "${bindir}/journal-gateway-zmtp-source ${bindir}/journal-gateway-zmtp-sink ${bindir}/journal-gateway-zmtp-control"

# systemd units
FILES_${PN} += "${systemd_unitdir}/system/journal-gateway-zmtp-sink.service"
FILES_${PN} += "${systemd_unitdir}/system/journal-gateway-zmtp-source.service"

# config files
FILES_${PN} += "${sysconfdir}/journal-gateway-zmtp-sink.conf"
FILES_${PN} += "${sysconfdir}/journal-gateway-zmtp-source.conf"
CONFFILES_${PN} += "${sysconfdir}/journal-gateway-zmtp-sink.conf"
CONFFILES_${PN} += "${sysconfdir}/journal-gateway-zmtp-source.conf"
