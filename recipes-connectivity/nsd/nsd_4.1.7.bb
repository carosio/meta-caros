DESCRIPTION = "NSD is an authoritative only, high performance, simple and open source name server."
HOMEPAGE = "http://www.nlnetlabs.nl/projects/nsd/"
SECTION = "console/network"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b6054f102a8cbe5266429041bafa39ca"

DEPENDS = "libevent"
RDEPENDS_${PN} = "openssl"

SRC_URI = "http://www.nlnetlabs.nl/downloads/nsd/nsd-4.1.7.tar.gz \
           file://nsd.service"

SRC_URI[md5sum] = "bedc9aa8fc235a0f082aad6e515b6127"
SRC_URI[sha256sum] = "7ba8beb29b495ab795e927d9aa82a5bb1b66296a78f61ed47d6ccfe59e9b1a8a"

FILES_${PN} += "${sysconfdir}/nsd \
                ${localstatedir}/lib/nsd \
                /run \
                /tmp"

CONFFILES_${PN} = "${sysconfdir}/nsd/nsd.conf"

inherit autotools useradd systemd

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "--system \
                       --home /var/lib/nsd \
                       --no-create-home \
                       --user-group \
                       nsd"

SYSTEMD_SERVICE_${PN} = "nsd.service"

# Specify any options you want to pass to the configure script using EXTRA_OECONF:
EXTRA_OECONF += "--with-libevent=${STAGING_LIBDIR}/.."
EXTRA_OECONF += "--with-ssl=${STAGING_LIBDIR}/.."

do_install_append() {
    install -d ${D}${localstatedir}/lib/nsd
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/nsd.service ${D}${systemd_unitdir}/system
}
