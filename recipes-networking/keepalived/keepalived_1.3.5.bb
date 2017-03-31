SUMMARY = "Keepalived is a routing software written in C"
DESCRIPTION = "The main goal of the keepalived project is to add a strong & robust \
keepalive facility to the Linux Virtual Server project."
HOMEPAGE = "http://www.keepalived.org"
SECTION = "network"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS += " pkgconfig iptables libnfnetlink libnl openssl net-snmp "
RDEPENDS_${PN} += " kernel-module-ip-vs \
    kernel-module-ip-vs-rr "

SRC_URI = "http://www.keepalived.org/software/keepalived-${PV}.tar.gz"

SRC_URI[md5sum] = "9964d295ec9d34ed3408b57d28847b68"
SRC_URI[sha256sum] = "c0114d86ea4c896557beb0d9367819a423ffba772bc5d7c548dc455e6b3bd048"

inherit autotools caros-service

RP="r1"

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "keepalived.service"
SYSTEMD_AUTO_ENABLE ?= "disable"

EXTRA_OECONF += " --with-init=systemd "

do_install_append() {
  rm -r ${D}/usr/share/snmp

  install -d ${D}${systemd_unitdir}/system
  install -m 0644 ${WORKDIR}/build/keepalived/keepalived.service ${D}${systemd_unitdir}/system
}
