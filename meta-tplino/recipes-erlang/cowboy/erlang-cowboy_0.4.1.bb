DESCRIPTION = "Small, fast, modular HTTP server."
SECTION = "devel"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0f736ba129c39537e16c83bdc97f8bba"

SRCREV="fd17b6d44412bf3678bdc9bfcfee19a90088ede4"
PR = "r1"

SRC_URI = "git://github.com/RoadRunnr/cowboy.git;protocol=git \
	   file://add-tetrapak.patch;apply=yes"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("cowboy", "cowboy-*", "ebin priv", "examples src include test", d)
}
