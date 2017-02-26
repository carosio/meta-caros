SUMMARY = "logging framework for Erlang"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ff253ad767462c46be284da12dda33e8"

PR = "r2"

SRC_URI = "https://github.com/basho/lager/archive/${PV}.tar.gz;downloadfilename=lager-${PV}.tar.gz"
SRC_URI += "file://fix-boss_compiler.patch;apply=yes"

SRC_URI[md5sum] = "ac45e2c062e0ce3067d431352f724549"
SRC_URI[sha256sum] = "1a1487286c013756c547b76dc8e25edfb5fcefb53834c3bf3ee5ae5140ae13e9"

DEPENDS = "erlang-goldrush"
RDEPENDS_${PN} += "erlang-goldrush erlang-syntax-tools erlang-compiler"

S = "${WORKDIR}/lager-${PV}"

inherit rebar
