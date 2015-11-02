DESCRIPTION = "A minimalistic user-space library oriented to Netlink developers"
SECTION = "libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=79808397c3355f163c012616125c9e26"

PR="r0.2"

DEPENDS = "libmnl"

SRC_URI = "git://git.netfilter.org/${PN};branch=master"
SRCREV="${AUTOREV}"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

