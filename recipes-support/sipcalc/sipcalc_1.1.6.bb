LICENSE = "other"
LIC_FILES_CHKSUM = "file://COPYING;md5=8d56d6727ab515f251f549660c447fae"
SUMMARY = "sipcalc - IP calculator"
DESCRIPTION = "sipcalc is a calculator for IP addresses, networks, IP ranges etc."

PR = "r3"

SRC_URI="http://www.routemeister.net/projects/sipcalc/files/sipcalc-${PV}.tar.gz \
        file://sipcalc.patch \
        file://extra-options.patch"

SRC_URI[md5sum]="e431c64387f2c8d20e96ad1d7931a845"
SRC_URI[sha256sum]="cfd476c667f7a119e49eb5fe8adcfb9d2339bc2e0d4d01a1d64b7c229be56357"

inherit autotools
