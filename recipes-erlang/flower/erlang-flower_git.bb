DESCRIPTION = "Erlang Openflow Controller lib"
SECTION = "devel"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://src/flower_app.erl;beginline=1;endline=19;md5=1ab28ac9e46c60331c620bdf33901f4f"
SRCREV="AUTOINC"

PR = "r0"

SRC_URI = "git://git@git.tpip.net/flower.git;protocol=ssh"

S = "${WORKDIR}/git"
DEPENDS += "erlang-regine erlang-gen-listener-tcp erlang-gen-socket"

inherit tetrapak

python () {
    erlang_def_package("erlang-flower", "flower-*", "ebin priv", "src include test README.md", d)
}