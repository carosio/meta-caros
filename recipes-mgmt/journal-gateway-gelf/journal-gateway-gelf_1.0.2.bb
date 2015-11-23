SUMMARY = "A gateway for sending logs from systemd's journald over an HTTP connection to a Graylog server in GELF format."
SECTION = "logging"
DEPENDS += "curl"
DEPENDS += "jansson"
DEPENDS += "systemd"
LICENSE = "LGPL-2.1"
PR = "r2"

LIC_FILES_CHKSUM = "file://LICENSE.LGPL2.1;md5=4fbd65380cdd255951079008b364516c"

inherit autotools caros-service

SRC_URI = "git://github.com/travelping/journal-gateway-gelf.git"
SRCREV = "153eb65939e016afbbe333adb94fa78eade078ff"

S = "${WORKDIR}/git"

SYSTEMD_AUTO_ENABLE = "disable"
SYSTEMD_SERVICE_${PN} += "${PN}.service"

do_compile() {
    echo ${S}
    cd ${S}
    oe_runmake
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/journal-gateway-gelf ${D}${bindir}

    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${S}/misc/journal-gateway-gelf.service ${D}${systemd_unitdir}/system/.

    install -d ${D}${sysconfdir}
    install -m 0644 ${S}/misc/journal-gateway-gelf.conf ${D}${sysconfdir}/.
}

FILES_${PN} = "${bindir}/journal-gateway-gelf ${bindir}/journal-gateway-gelf-control"

# systemd units
FILES_${PN} += "${systemd_unitdir}/system/journal-gateway-gelf.service"

# config files
FILES_${PN} += "${sysconfdir}/journal-gateway-gelf.conf"
CONFFILES_${PN} += "${sysconfdir}/journal-gateway-gelf.conf"
