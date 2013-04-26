DESCRIPTION = "gen_server2 OTP behaviour - provided as an OTP library"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=6cfe87e918c30c45d02d316752aee8ec"

SRCREV="2803f68b002a24cd4ed3d1c7d304a94c7f5a2074"
PR = "r0"

SRC_URI = "git://git@git.tpip.net/gen_server2.git;protocol=ssh"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("gen-server2", "gen_server2*", "ebin priv", "src include", d)
}

