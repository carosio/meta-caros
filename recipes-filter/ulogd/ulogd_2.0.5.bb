SUMMARY = "Connection tracking userspace tools for Linux"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=c93c0550bd3173f4504b2cbd8991e50b"
DEPENDS = "libnfnetlink libmnl libnetfilter-conntrack libnetfilter-log \
           libnetfilter-acct"

SRC_URI = "http://www.netfilter.org/projects/ulogd/files/${PN}-${PV}.tar.bz2"
SRC_URI[md5sum] = "f0f8fe1371fa28b5e06cb16aaa0111dd"
SRC_URI[sha256sum] = "a221cb9f77347c0ca00d0937e27c1b90e3291a553cc62a4139b788e2e420e8c0"

inherit autotools pkgconfig

EXTRA_OECONF = "--enable-ulog \
		--enable-nflog \
		--enable-nfct \
		--enable-nfacct \
		--without-pgsql \
		--without-mysql \
		--without-sqlite \
		--without-dbi \
		--without-pcap \
		--without-jansson"
