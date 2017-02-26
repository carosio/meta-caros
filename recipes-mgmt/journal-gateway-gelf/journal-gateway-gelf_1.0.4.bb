SUMMARY = "A gateway for sending logs from systemd's journald over an HTTP connection to a Graylog server in GELF format."
SECTION = "logging"
DEPENDS += "curl"
DEPENDS += "jansson"
DEPENDS += "systemd"
LICENSE = "LGPL-2.1"
PR = "r1"

LIC_FILES_CHKSUM = "file://LICENSE.LGPL2.1;md5=4fbd65380cdd255951079008b364516c"

inherit autotools caros-service

SRC_URI = "git://github.com/travelping/journal-gateway-gelf.git"
SRCREV = "e9f7c881ab89757348d834aac7c849a519eb9612"

S = "${WORKDIR}/git"

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

FILES_${PN} = "${bindir}/journal-gateway-gelf"

# config files
FILES_${PN} += "${sysconfdir}/journal-gateway-gelf.conf"
CONFFILES_${PN} += "${sysconfdir}/journal-gateway-gelf.conf"
