DESCRIPTION = "ISC Internet Domain Name Server"
HOMEPAGE = "http://www.isc.org/sw/bind/"
SECTION = "console/network"

LICENSE = "ISC & BSD"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=70a3b234bb402e38258a6aa54b3d25f3"

DEPENDS = "openssl libcap"
PR = "r11"

# --enable-exportlib is necessary for building dhcp
EXTRA_OECONF = " --enable-ipv6=no --with-randomdev=/dev/random --disable-threads \
                 --disable-devpoll --disable-epoll --with-gost=no \
                 --with-gssapi=no \
                 --sysconfdir=${sysconfdir}/bind \
                 --with-openssl=${STAGING_LIBDIR}/.. --with-libxml2=${STAGING_LIBDIR}/.. \
                 --enable-exportlib --with-export-includedir=${includedir} --with-export-libdir=${libdir} \
                 --with-ecdsa=no --without-readline"

inherit autotools

PARALLEL_MAKE = ""

PACKAGES_prepend = "${PN}-utils "
FILES_${PN}-utils = "${bindir}/host ${bindir}/dig ${bindir}/nslookup"
FILES_${PN}-dev += "${bindir}/isc-config.h"
