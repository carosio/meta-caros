SUMMARY = "Program for providing universal TLS/SSL tunneling service"
HOMEPAGE = "http://www.stunnel.org"
SECTION = "net"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=866cdc7459d91e092b174388fab8d283"

SRC_URI = "https://www.stunnel.org/downloads/stunnel-5.17.tar.gz"
SRC_URI[md5sum] = "e70f001ee190105c5a10a74f4bd54901"
SRC_URI[sha256sum] = "c3e79e582621a0827125e35e1c00450190104fc02dc3c5274cb02b05859fd472"

PR = "r1"

DEPENDS = "openssl zlib tcp-wrappers"

inherit autotools

EXTRA_OECONF = "--with-ssl='${STAGING_EXECPREFIXDIR}' --disable-fips"

PACKAGECONFIG ??= "${@base_contains('DISTRO_FEATURES','systemd','systemd','',d)}"
PACKAGECONFIG[systemd] = "--enable-systemd,--disable-systemd,systemd"
