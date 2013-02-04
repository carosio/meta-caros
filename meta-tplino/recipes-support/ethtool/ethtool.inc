DESCRIPTION = "A small utility for examining and tuning the settings \
of your ethernet-based network interfaces."
HOMEPAGE = "http://www.kernel.org/pub/software/network/ethtool/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

inherit autotools

SRC_URI = "${KERNELORG_MIRROR}/software/network/ethtool/${P}.tar.gz"

