DESCRIPTION = "Dynamic Compile Library"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://Makefile;md5=ea83b77d17ce2d1535be8911bc6e0b95"

SRCREV = "a718c2af3457bf86e6ee06c50da48a886d7df728"
PR = "r2"
PV-orig := "${PV}" 
PV = "${PV-orig}-${PR}-${SRCPV}" 

SRC_URI = "git://git@git.tpip.net/dynamic_compile;protocol=ssh"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("dynamic-compile", "dynamic_compile*", "ebin priv", "dynamic_compile.epm src include", d)
}
