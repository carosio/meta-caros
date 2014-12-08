DESCRIPTION = "logging framework for Erlang"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ff253ad767462c46be284da12dda33e8"

PR = "r1"

SRC_URI = "https://github.com/basho/lager/archive/${PV}.tar.gz;downloadfilename=lager-${PV}.tar.gz \
           file://add-tetrapak.patch;apply=yes \
           file://fix-boss_compiler.patch;apply=yes"
SRC_URI[md5sum] = "e02f00903c13fe126823c40f04d245e6"
SRC_URI[sha256sum] = "a659b4bb8c3e126854f38826b3418d595e2fb4b147c66a598d194677f24ca3a6"

DEPENDS = "erlang-goldrush"

S = "${WORKDIR}/lager-${PV}"

TETRAPAK_OPTS += "-o build.version ${PV}-${PR}"

inherit tetrapak

python () {
    erlang_def_package("lager", "lager-*", "ebin", "include src test LICENSE README.md tools.mk", d)
}
