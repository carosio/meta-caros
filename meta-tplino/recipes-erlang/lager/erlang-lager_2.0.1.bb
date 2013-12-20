DESCRIPTION = "logging framework for Erlang"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ff253ad767462c46be284da12dda33e8"

SRCREV = "ad400896af5b1ad8b4f7a4d34e609b5a990640bb"
PR = "r2"

SRC_URI = "git://github.com/basho/lager.git;protocol=git \
           file://add-tetrapak.patch;apply=yes \
           file://fix-boss_compiler.patch;apply=yes"

DEPENDS_append = " erlang-goldrush "
RDEPENDS_${PN}_append = " erlang-goldrush "

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("lager", "lager-*", "ebin", "include src test LICENSE README.md", d)
}
