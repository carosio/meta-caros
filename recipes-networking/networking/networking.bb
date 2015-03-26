SUMMARY = "Starting the network"
SECTION = "net"
LICENSE = "CLOSED"

PR = "r3"

RPROVIDES_${PN} = "networking.service"

SRC_URI = "file://start_interfaces.service"

inherit systemd

SYSTEMD_SERVICE_${PN} = "start_interfaces.service"


do_install() {
    install -d ${D}${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/start_interfaces.service ${D}/${systemd_unitdir}/system
}
