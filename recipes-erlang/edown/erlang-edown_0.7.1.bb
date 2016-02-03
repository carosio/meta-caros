SUMMARY = "Edown"
DISCRIPTION="Edown - Markdown generated from Edoc"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://src/edown.app.src;beginline=0;endline=16;md5=735d9ef9223b9ad830d4449fbfb0b90b"

PR = "r1"

inherit rebar

SRC_URI="git://github.com/uwiger/edown"
SRCREV="abd9f5bbf9265a0a25640a677824386e199e91a6"

S="${WORKDIR}/git"

RDEPENDS_${PN} += "erlang-edoc"
