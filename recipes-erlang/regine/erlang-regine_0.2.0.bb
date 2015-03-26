SUMMARY = "Regine - generic process registry"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/regine_server.erl;beginline=1;endline=19;md5=0bd3de365e18dfaf63708df79addef84"
SRCREV="f751a9f59bd1f9cde7323ef50d6dbdc0871544e3"

SRC_URI = "git://git@git.tpip.net/regine.git;protocol=ssh"
PR = "r1"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("regine", "regine-*", "ebin priv", "NEWS.md src include", d)
}
