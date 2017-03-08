DESCRIPTION = "CAROS networking packagegroup"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PR = "r3"

inherit packagegroup

PACKAGES = "${PN}"
PACKAGE_GROUP_caros_networking = "${PN}"

RDEPENDS_${PN} = "\
	bird \
	bridge-utils \
	freeradius-server \
	iptables \
	nftables \
	openvswitch \
	pmacct \
"
