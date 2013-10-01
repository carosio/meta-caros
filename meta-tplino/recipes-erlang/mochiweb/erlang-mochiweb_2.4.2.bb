DESCRIPTION = "MochiMedia Web Server"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5e0144b1f4eb0e6001053e5e37e97db2"

SRCREV="edb954a15c4343d56345776e3f5c69e201714153"
PR = "r1"


SRC_URI = "git://github.com/mochi/mochiweb.git;protocol=git \
	   file://add-tetrapak.patch;apply=yes"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("mochiweb", "mochiweb-*", "ebin priv", "src include LICENSE scripts examples", d)
}
