DESCRIPTION = "tetrapak is an extensible build system for Erlang/OTP applications."
SECTION = "devel"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://src/gen_socket.erl;beginline=1;endline=35;md5=a6cec856fb731da7bc4c758a3ea0d365"
SRCREV="AUTOINC"

PR = "r1"

SRC_URI = "git://git@git.tpip.net/gen_socket.git;protocol=ssh"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("gen-socket", "gen_socket-*", "ebin priv", "src c_src include README.md", d)
}