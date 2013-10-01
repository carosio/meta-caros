DESCRIPTION = "Erlang Round Robin Databases"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=32b4bd26a9ae3f739e3ba06649f2905a"

SRCREV="46d536e4c955d148665dc9580548314bc130778b"
PR = "r1"

SRC_URI = "git://git@git.tpip.net/errd.git;protocol=ssh"

S = "${WORKDIR}/git"

inherit tetrapak

RDEPENDS_${PN}_append = " rrdtool "

python () {
    erlang_def_package("errd", "errd*", "ebin priv", "src include doc", d)
}
