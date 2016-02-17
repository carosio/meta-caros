SUMMARY = "Tools for managing kernel packet filtering capabilities"
DESCRIPTION = "iptables is the userspace command line program used to configure and control network packet \
filtering code in Linux."
HOMEPAGE = "http://www.netfilter.org/"
BUGTRACKER = "http://bugzilla.netfilter.org/"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d1a78fdd879a263a5e0b42d1fc565e79"

PR = "r1.5"

DEPENDS = "libmnl libnftnl readline gmp iptables"
RRECOMMENDS_${PN} = "kernel-module-nf-tables \
		     kernel-module-nf-tables-arp \
		     kernel-module-nf-tables-bridge \
		     kernel-module-nf-tables-inet \
		     kernel-module-nft-compat \
		     kernel-module-nft-counter \
		     kernel-module-nft-ct \
		     kernel-module-nft-exthdr \
		     kernel-module-nft-hash \
		     kernel-module-nft-limit \
		     kernel-module-nft-log \
		     kernel-module-nft-meta \
		     kernel-module-nft-nat \
		     kernel-module-nft-queue \
		     kernel-module-nft-rbtree \
		     kernel-module-nft-reject \
		     kernel-module-nft-reject-inet \
		     kernel-module-nf-tables-ipv4 \
		     kernel-module-nft-chain-nat-ipv4 \
		     kernel-module-nft-chain-route-ipv4 \
		     kernel-module-nft-reject-ipv4 \
		     kernel-module-nf-tables-ipv6 \
		     kernel-module-nft-chain-route-ipv6 \
		     kernel-module-nft-reject-ipv6"

CONFFILES_${PN} = "${sysconfdir}/nftables/*"

SRC_URI = "http://netfilter.org/projects/${PN}/files/${PN}-${PV}.tar.bz2"

SRC_URI[md5sum] = "94bfe1c54bcb9f6ed974835f2fca8069"
SRC_URI[sha256sum] = "1fb6dff333d8a4fc347cbbe273bf905a2634b27a8c39df0d3a45d5a3fde10ad6"

inherit autotools pkgconfig
