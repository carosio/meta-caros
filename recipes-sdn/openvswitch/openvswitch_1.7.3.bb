require openvswitch.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=49eeb5acb1f5e510f12c44f176c42253"

SRC_URI += "http://openvswitch.org/releases/openvswitch-${PV}.tar.gz \
	    file://preparation-for-linux-3.7.0+.patch \
	    "

SRC_URI[md5sum] = "aa0d4fefb587469b4cc70f657da58b7d"
SRC_URI[sha256sum] = "b750ce656031ec5f150f96834088d5096e351a7578fe0057f1e040fde0d5fdb1"

PR = "r1"
