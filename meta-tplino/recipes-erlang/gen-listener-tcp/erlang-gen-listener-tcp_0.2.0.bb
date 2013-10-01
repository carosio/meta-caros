DESCRIPTION = "A generic tcp listener process adhering to OTP design principles"
SECTION = "devel"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
SRCREV="0bdf5829a8f7937625f0639361b2016155679d1b"

PR = "r1"

SRC_URI = "git://git@git.tpip.net/gen_listener_tcp.git;protocol=ssh"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("gen-listener-tcp", "gen_listener_tcp-*", "ebin priv", "src include README.md examples", d)
}
