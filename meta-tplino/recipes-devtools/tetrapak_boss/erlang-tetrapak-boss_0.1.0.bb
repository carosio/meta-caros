DESCRIPTION = "Tetrapak Boss Plugin"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=a2a1073b572c6d211c2062a7d529fec5"

SRCREV="10201927e7b03232af1ac314c62d032a8f5818ef"
PR = "r2"
PV-orig := "${PV}" 
PV = "${PV-orig}-${PR}-${SRCPV}" 

SRC_URI = "git://git@git.tpip.net/tetrapak_boss.git;protocol=ssh"

S = "${WORKDIR}/git"

DEPENDS_append = " erlang-boss "

inherit tetrapak

python () {
    erlang_def_package("tetrapak-boss", "tetrapak_boss*", "ebin", "src include log tetrapak", d)
}
