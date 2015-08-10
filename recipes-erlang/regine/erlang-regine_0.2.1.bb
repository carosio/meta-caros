SUMMARY = "Regine - generic process registry"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/regine_server.erl;beginline=1;endline=19;md5=0bd3de365e18dfaf63708df79addef84"

SRC_URI = "git://github.com/travelping/regine.git;tag=${PV}"
PR = "r1"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("regine", "regine-*", "ebin priv", "NEWS.md src include rebar.config", d)
}
