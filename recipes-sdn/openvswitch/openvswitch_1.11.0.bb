FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}_git:"

require openvswitch.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=49eeb5acb1f5e510f12c44f176c42253"

SRC_URI += "http://openvswitch.org/releases/openvswitch-1.11.0.tar.gz \
	    file://fix_missing_dist_file.patch \
	   "

S = "${WORKDIR}/git"

PR = "r1"

