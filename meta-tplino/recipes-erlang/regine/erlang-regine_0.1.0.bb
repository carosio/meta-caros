DESCRIPTION = "Regine - generic process registry"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/regine_server.erl;beginline=1;endline=19;md5=0bd3de365e18dfaf63708df79addef84"
SRCREV="${AUTOREV}"

PR = "r1"
PV-orig := "${PV}" 
PV = "${PV-orig}-${PR}-${SRCPV}" 
SRC_URI = "git://git@git.tpip.net/regine.git;protocol=ssh"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("regine", "regine-*", "ebin priv", "src include", d)
}
