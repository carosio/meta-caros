DESCRIPTION = "logging framework for Erlang"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ff253ad767462c46be284da12dda33e8"

SRCREV = "${AUTOREV}"
PV = "git${SRCPV}"
PR = "r0"

DEFAULT_PREFERENCE = "-1"

SRC_URI = "git://github.com/basho/lager.git;protocol=git \
           file://add-tetrapak.patch \
           file://fix-boss_compiler.patch \
	   file://add-map-printing-support.patch"

DEPENDS += "erlang-goldrush"
RDEPENDS_${PN} += "erlang-goldrush"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("lager", "lager-*", "ebin", "include src test LICENSE README.md tools.mk", d)
}
