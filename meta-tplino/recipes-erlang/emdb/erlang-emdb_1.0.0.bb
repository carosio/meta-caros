DESCRIPTION = "Memory Mapped Database - Erlang bindings"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/emdb.erl;beginline=1;endline=8;md5=8e7aed6247005d59f8b275eb7143e67a"

PR = "r1"

SRC_URI = "git://git@git.tpip.net/emdb.git;protocol=ssh"
SRCREV="6086085d82e4052b1c8bd8b8f5f02e3e687f7979"
	
S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("emdb", "emdb-*", "ebin priv", "src include LICENSE ChangeLog README.md NEWS.md test", d)
}
