DESCRIPTION = "Erlang Openflow Controller lib"
SECTION = "devel"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://MIT-LICENSE.txt;md5=9db35a42b923528200d82fcbb74c4dc2"

SRC_URI = "git://git@git.tpip.net/flower.git;protocol=ssh"

S = "${WORKDIR}/git"

DEPENDS += "erlang-regine erlang-gen-listener-tcp erlang-gen-socket"

inherit tetrapak

python () {
    erlang_def_package("flower", "flower-*", "ebin priv", "src include test NEWS.md README.md MIT-LICENSE.txt", d)
}
