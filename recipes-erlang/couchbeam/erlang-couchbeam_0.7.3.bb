SUMMARY = "Erlang CouchDB kit"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=212a03dbdaf546250bb9c74b04bfdeae"

PR = "r3"

SRC_URI = "git://github.com/travelping/couchbeam.git;protocol=git;branch=tp-integration"
SRCREV="3d5275eb412439007ee24f4342ce73a004848809"

S = "${WORKDIR}/git"

inherit tetrapak

DEPENDS += "erlang-ejson erlang-ibrowse erlang-mochiweb erlang-oauth"

python () {
    erlang_def_package("couchbeam", "couchbeam-*", "ebin priv", "src include examples t NOTICE LICENSE rebar rebar.config NEWS", d)
}
