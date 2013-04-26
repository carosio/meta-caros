DESCRIPTION = "Client interface to MongoDB"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=e9afc8235a516dcad63385790d64ab22"

SRCREV="ebdab7eb1a1e24880d25c22cfedff8a3c3abdb77"
PR = "r0"

SRC_URI = "git://git@git.tpip.net/mongodb.git;protocol=ssh"

S = "${WORKDIR}/git"

inherit tetrapak

DEPENDS_append = " erlang-bson "
RDEPENDS_${PN}_append = " erlang-bson "

python () {
    erlang_def_package("mongodb", "mongodb*", "ebin priv", "src include doc", d)
}
