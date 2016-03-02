SUMMARY = "dnssd_erlang"
DESCRIPTION = "dnssd_erlang is an interface to Apple's Bonjour DNS Service Discoveryi implementation."
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ff253ad767462c46be284da12dda33e8"

PR = "r0"

inherit rebar

DEPENDS += "avahi"

SRC_URI="git://github.com/benoitc/dnssd_erlang"
SRCREV = "9bf2a7b9ff598c4579de7f6c029916ed5d5a1193"

S="${WORKDIR}/git"

FILES_${PN}-dbg += " /usr/src/debug/dnssd \
  ${libdir}/erlang/lib/dnssd/lib/dnssd-0.9/priv/.debug/dnssd_drv.so"
