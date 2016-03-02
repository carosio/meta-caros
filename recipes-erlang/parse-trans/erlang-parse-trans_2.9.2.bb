SUMMARY = "The parse_trans application"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://src/parse_trans.erl;beginline=1;endline=15;md5=1ed0348b020ad0391988878f5548303e"

PR = "r1"

SRC_URI="git://github.com/uwiger/parse_trans.git"
SRCREV="a210adafdfbb904d156d8f22abd5fb58fc17de1e"

S="${WORKDIR}/git"

DEPENDS += "erlang-edown"
RDEPENDS_${PN} += "erlang-edown"

inherit rebar
