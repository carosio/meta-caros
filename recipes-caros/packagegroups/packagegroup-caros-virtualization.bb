DESCRIPTION = "CAROS virtualization packagegroup"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PR = "r1"

inherit packagegroup

PACKAGES = "${PN}"
PACKAGE_GROUP_caros_virtualization = "${PN}"

RDEPENDS_${PN} = "\
	iptables \
	libvirt-libvirtd \
	libvirt-virsh \
	dnsmasq \
	openvswitch \
"
