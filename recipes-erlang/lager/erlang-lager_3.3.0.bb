SUMMARY = "logging framework for Erlang"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ff253ad767462c46be284da12dda33e8"

PR = "r1"

SRC_URI = "https://github.com/erlang-lager/lager/archive/${PV}.tar.gz;downloadfilename=lager-${PV}.tar.gz"
SRC_URI += "file://fix-boss_compiler.patch;apply=yes"

SRC_URI[md5sum] = "8413530ab73b3fc6d9486832a945926a"
SRC_URI[sha256sum] = "6626d868300a39805b377e7c8ecc5210695159bb557ccafffd716fd55f1b6396"

DEPENDS = "erlang-goldrush"
RDEPENDS_${PN} += "erlang-goldrush erlang-syntax-tools erlang-compiler"

S = "${WORKDIR}/lager-${PV}"

inherit rebar
