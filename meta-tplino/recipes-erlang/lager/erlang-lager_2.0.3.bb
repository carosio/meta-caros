DESCRIPTION = "logging framework for Erlang"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ff253ad767462c46be284da12dda33e8"

SRCREV = "b6b6cebcb27ccff8acc59ae775acebc2f52e4926"
PR = "r1"

SRC_URI = "git://github.com/basho/lager.git;protocol=git \
           file://add-tetrapak.patch;apply=yes \
           file://fix-boss_compiler.patch;apply=yes \
	   file://add-map-printing-support.patch"

DEPENDS_append = " erlang-goldrush "
RDEPENDS_${PN}_append = " erlang-goldrush "

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("lager", "lager-*", "ebin", "include src test LICENSE README.md tools.mk", d)
}
