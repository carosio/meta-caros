SUMMARY = "Small set of multi-purpose passive network monitoring tools"
HOMEPAGE = "http://www.pmacct.net/"
SECTION = "network"
LICENSE = "GPL-2"
LIC_FILES_CHKSUM = "file://COPYING;md5=56015ca09d32459cf5a3c0e574f86f16"

SRC_URI = "http://www.pmacct.net/${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "5dc1dab96fbd3937b49152d1bdc2913f"
SRC_URI[sha256sum] = "eb332a6812d1e02134900a1d115a24f315de7c861a9b63093c1226753486cbe7"

PR = "r1"

DEPENDS = "libpcap zlib"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit autotools pkgconfig

EXTRA_OECONF = "--with-pcap-includes=${STAGING_INCDIR}"
