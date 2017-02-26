SUMMARY = "Erlang Coveralls"
SECTION = "devel"
LICENSE = "other"
LIC_FILES_CHKSUM = "file://COPYING;md5=9eb29a4096c87b49d3cd9cfe9b9fa58e"

PR = "r1"

inherit rebar

SRC_URI="git://github.com/markusn/coveralls-erl.git"
SRCREV="57a190502cd2606d2b5cc142bd5cec1ec7c497b2"

S="${WORKDIR}/git"
