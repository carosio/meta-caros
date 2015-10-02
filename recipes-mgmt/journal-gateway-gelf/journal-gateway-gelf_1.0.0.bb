SUMMARY = "A gateway for sending logs from systemd's journald over an HTTP connection to a Graylog server in GELF format."
SECTION = "logging"
DEPENDS += "curl"
DEPENDS += "jansson"
DEPENDS += "systemd"
LICENSE = "LGPL-2.1"
PR = "r1"

LIC_FILES_CHKSUM = "file://LICENSE.LGPL2.1;md5=4fbd65380cdd255951079008b364516c"

inherit autotools systemd

SRC_URI = "git://github.com/travelping/journal-gateway-gelf.git"
SRCREV = "e8afa1443847434245af2a68406d25aaca508388"

S = "${WORKDIR}/git"

SYSTEMD_AUTO_ENABLE = "disable"
SYSTEMD_SERVICE_${PN} += "${PN}-source.service"

do_compile() {
    echo ${S}
    cd ${S}
    oe_runmake
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/journal-gateway-gelf-source ${D}${bindir}

    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${S}/misc/journal-gateway-gelf-source.service ${D}${systemd_unitdir}/system/.

    install -d ${D}${sysconfdir}
    install -m 0644 ${S}/misc/journal-gateway-gelf-source.conf ${D}${sysconfdir}/.
}

FILES_${PN} = "${bindir}/journal-gateway-gelf-source ${bindir}/journal-gateway-gelf-control"

# systemd units
FILES_${PN} += "${systemd_unitdir}/system/journal-gateway-gelf-source.service"

# config files
FILES_${PN} += "${sysconfdir}/journal-gateway-gelf-source.conf"
CONFFILES_${PN} += "${sysconfdir}/journal-gateway-gelf-source.conf"
