DESCRIPTION = "Unbound is a validating, recursive, and caching DNS resolver."
HOMEPAGE = "https://www.unbound.net"
SECTION = "console/network"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5308494bc0590c0cb036afd781d78f06"

DEPENDS = "openssl expat"

SRC_URI = "https://www.unbound.net/downloads/${PN}-${PV}.tar.gz \
           file://unbound.service"

SRC_URI[md5sum] = "a1253cbbb339dbca03404dcc58365d71"
SRC_URI[sha256sum] = "4b2088e5aa81a2d48f6337c30c1cf7e99b2e2dc4f92e463b3bee626eee731ca8"

FILES_${PN} += "${sysconfdir}/unbound \
               ${localstatedir}/lib/unbound"

CONFFILES_${PN} = "${sysconfdir}/unbound/unbound.conf"

inherit autotools systemd useradd

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "--system \
                       --home /var/lib/unbound \
                       --no-create-home \
                       --user-group \
                       unbound"

SYSTEMD_SERVICE_${PN} = "unbound.service"

# Specify any options you want to pass to the configure script using EXTRA_OECONF:
EXTRA_OECONF += "--with-ssl=${STAGING_LIBDIR}/.."
EXTRA_OECONF += "--with-libexpat=${STAGING_LIBDIR}/.."
EXTRA_OECONF += "libtool=./${TARGET_SYS}-libtool"
EXTRA_OECONF += "--disable-rpath"

do_install_append() {
    install -d "${D}${localstatedir}/lib/unbound"
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/unbound.service ${D}${systemd_unitdir}/system
}
