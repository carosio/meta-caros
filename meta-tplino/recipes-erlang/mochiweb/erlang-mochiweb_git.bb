DESCRIPTION = "MochiMedia Web Server"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5e0144b1f4eb0e6001053e5e37e97db2"

SRCREV="e6d1870200802f32e17e334272191397e15aec53"
PV = "2.3.0+git${SRCPV}"
PR = "r0"

SRC_URI = "git://github.com/mochi/mochiweb.git;protocol=git \
	   file://add-tetrapak.patch;apply=yes"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("mochiweb", "mochiweb-*", "ebin priv", "src include LICENSE scripts examples", d)
}