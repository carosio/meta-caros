SUMARY = "ldns - a dns library in the C language"
HOMEPAGE = "http://www.nlnetlabs.nl/projects/ldns/"
SECTION = "console/network"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=34330f15b2b4abbbaaa7623f79a6a019"

PR = "r1"

DEPENDS = "openssl libtool"
RDEPENDS_${PN} = "openssl"

SRC_URI = "http://www.nlnetlabs.nl/downloads/ldns/ldns-${PV}.tar.gz"

SRC_URI[md5sum] = "a79423bcc4129e6d59b616b1cae11e5e"
SRC_URI[sha256sum] = "8b88e059452118e8949a2752a55ce59bc71fa5bc414103e17f5b6b06f9bcc8cd"

FILES_${PN} += "${libdir} ${bindir}"

inherit autotools

EXTRA_OECONF += "--with-ssl=${STAGING_LIBDIR}/.."
EXTRA_OECONF += "--with-drill"

FILES_${PN}-dev += "${bindir}/ldns-config"

do_configure_append() {
	# force use of our libtool
	sed -i 's/^libtool.*$//' Makefile
	sed -i "s/^LIBTOOL.*\$/LIBTOOL = ${TARGET_PREFIX}libtool/" Makefile
}
