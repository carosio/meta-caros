DESCRIPTION = "Jansson is a C library for encoding, decoding and manipulating JSON data."
HOMEPAGE = "http://www.digip.org/jansson/"
LICENSE = "MIT"
INC_PR = "r1"
SRC_URI = "http://www.digip.org/jansson/releases/${PN}-${PV}.tar.gz"
inherit autotools

LIC_FILES_CHKSUM = "file://LICENSE;md5=74ead53343bf648f8f588341b2f8fa16"
