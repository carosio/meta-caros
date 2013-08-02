DESCRIPTION = "MySQL Library"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=419886945d428c4b26dd4a4f5b2b222b"

SRCREV = "9cb511a262421b8d502c7d3b850c4ad54a1a9d09"
PR = "r1"
PV-orig := "${PV}" 
PV = "${PV-orig}-${PR}-${SRCPV}" 

SRC_URI = "git://git@git.tpip.net/mysql;protocol=ssh"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("mysql", "mysql*", "ebin priv", "src include test tetrapak", d)
}
