DESCRIPTION = "Memory Mapped Database - Erlang bindings"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/emdb.erl;beginline=1;endline=8;md5=8e7aed6247005d59f8b275eb7143e67a"

SRC_URI = "git://git@git.tpip.net/emdb.git;protocol=ssh"
SRCREV = "${AUTOREV}"
PV = "git${SRCPV}"
PR = "r0"

DEFAULT_PREFERENCE = "-1"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("emdb", "emdb-*", "ebin priv", "src include LICENSE ChangeLog README.md NEWS.md test", d)
}
