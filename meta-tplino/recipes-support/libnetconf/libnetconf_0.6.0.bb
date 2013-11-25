DESCRIPTION = "libnetconf is a NETCONF library in C intended for building NETCONF clients and servers."
HOMEPAGE = "https://code.google.com/p/libnetconf/"
SECTION = "libs"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=0ae3cf1050f91136805088c0b126d47b"

SRC_URI = " \
    git://code.google.com/p/libnetconf/;protocol=https;branch=master \
    file://remove_doc_install.patch \
"

SRCREV="a2a6cf4c374e165642c4298a9713a714bce744c5"
PR = "r1"

DEPENDS = "libxslt libssh2 openssl curl"

S = "${WORKDIR}/git"

inherit autotools gettext

EXTRA_OECONF = "libtool=${STAGING_BINDIR_CROSS}/${HOST_SYS}-libtool"
