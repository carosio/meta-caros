DESCRIPTION = "Erlang RADIUS server"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://MIT_LICENSE;md5=7a8830d51731fc79efbb8166db8d2859"

S = "${WORKDIR}/git"

SRCREV="fc6461f33cbbfd0032dde86d9e56a8b4481205b2"

PR = "r1"

SRC_URI = "git://github.com/travelping/eradius.git;protocol=git"

inherit tetrapak

python () {
    erlang_def_package("eradius", "eradius-*", "ebin priv", "src include mibs test NEWS.md README.md MIT_LICENSE .arcconfig", d)
}
