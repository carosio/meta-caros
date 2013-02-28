FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}_git:"

require openvswitch.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=49eeb5acb1f5e510f12c44f176c42253"

SRC_URI += "git://openvswitch.org/openvswitch;protocol=git \
	    file://fix_missing_dist_file.patch \
	   "

S = "${WORKDIR}/git"

SRCREV="AUTOINC"
PV = "git${SRCPV}"
PR = "r0"

