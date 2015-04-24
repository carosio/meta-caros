FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}_git:"

require openvswitch.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=5973c953e3c8a767cf0808ff8a0bac1b"

SRC_URI_prepend = "http://openvswitch.org/releases/${PN}-${PV}.tar.gz "
SRC_URI += "file://remove-depmod.patch"

SRC_URI[md5sum] = "9c4d1471a56718132e0157af1bfc9310"
SRC_URI[sha256sum] = "011052645cd4c7afee2732e87d45e589a0540ac7b7523027d3be2d7c7db7c899"

PR = "${INC_PR}.1"

