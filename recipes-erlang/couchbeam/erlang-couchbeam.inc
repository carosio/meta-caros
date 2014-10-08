DESCRIPTION = "Erlang CouchDB kit"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=212a03dbdaf546250bb9c74b04bfdeae"

INC_PR = "r1"

SRC_URI = "git://github.com/travelping/couchbeam.git;protocol=git;branch=tp-integration"

S = "${WORKDIR}/git"

inherit tetrapak

DEPENDS += "erlang-ejson erlang-ibrowse erlang-mochiweb erlang-oauth"
RDEPENDS_${PN}_append = " erlang-ejson "

python () {
    erlang_def_package("couchbeam", "couchbeam-*", "ebin priv", "src include examples t NOTICE LICENSE", d)
}
