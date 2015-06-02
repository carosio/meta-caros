SUMMARY = "Erlang Round Robin Databases"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=32b4bd26a9ae3f739e3ba06649f2905a"

SRCREV="04c6fb8ad2b16bd93dcb3cc3fb03b2a693746f59"
PR = "r2"

SRC_URI = "git://github.com/carosio/errd.git"

S = "${WORKDIR}/git"

inherit tetrapak

RDEPENDS_${PN}_append = " rrdtool "

python () {
    erlang_def_package("errd", "errd*", "ebin priv", "src include doc", d)
}
