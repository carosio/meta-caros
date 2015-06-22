SUMMARY = "Memory Mapped Database - Erlang bindings"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/emdb.erl;beginline=1;endline=8;md5=8e7aed6247005d59f8b275eb7143e67a"

PR = "r4"

DEPENDS += "erlang-pmod-transform"

SRC_URI = "git://github.com/carosio/emdb.git;branch=travelping"
SRCREV="ab642f3327d7fec56b4dc6607e640ad58cbcc863"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("emdb", "emdb-*", "ebin priv", "src include LICENSE ChangeLog README.md NEWS.md test", d)
}
