FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}_git:"

require openvswitch.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=49eeb5acb1f5e510f12c44f176c42253"

SRC_URI += "http://openvswitch.org/releases/openvswitch-1.11.0.tar.gz \
	   "

SRC_URI[md5sum] = "81231a77dcd38181dbc1cb701e4fc9d0"
SRC_URI[sha256sum] = "007d7d3f2deabe5a3845d1045d23b6b1de174497a8e436091541221dd71833da"

PR = "r3"

