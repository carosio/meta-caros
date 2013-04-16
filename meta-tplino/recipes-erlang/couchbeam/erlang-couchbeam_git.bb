DESCRIPTION = "Erlang CouchDB kit"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=212a03dbdaf546250bb9c74b04bfdeae"

SRCREV="d34ebb6ea97260c3dd775d796bff67c87b2717de"
PV = "0.7.1+git${SRCPV}"
PR = "r1"

SRC_URI = "git://github.com/travelping/couchbeam.git;protocol=git;branch=tp-integration \
	   file://add-tetrapak.patch;apply=yes"

S = "${WORKDIR}/git"

inherit tetrapak

DEPENDS += "erlang-ejson erlang-ibrowse erlang-mochiweb erlang-oauth"
RDEPENDS_${PN}_append = " erlang-ejson "

python () {
    erlang_def_package("couchbeam", "couchbeam-*", "ebin priv", "src include examples t NOTICE LICENSE", d)
}
