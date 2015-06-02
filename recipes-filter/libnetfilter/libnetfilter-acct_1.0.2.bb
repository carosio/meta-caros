SUMMARY = "Netfilter accounting library"
DESCRIPTION = "Userspace library providing a programming interface (API) to the Linux kernel netfilter extended accounting infrastructure"
HOMEPAGE = "http://www.netfilter.org/projects/libnetfilter_acct/index.html"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"
DEPENDS = "libmnl"

SRC_URI = "http://www.netfilter.org/projects/libnetfilter_acct/files/libnetfilter_acct-${PV}.tar.bz2;name=tar"
SRC_URI[tar.md5sum] = "2118d9514c079839ebd9cb3144ad2ad7"
SRC_URI[tar.sha256sum] = "0128f19c3419fbd84f7e6d46b13a33ef7bda9b9f5e493bc5ae1882d087514b71"

S = "${WORKDIR}/libnetfilter_acct-${PV}"

inherit autotools pkgconfig
