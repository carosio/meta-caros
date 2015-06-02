SUMMARY = "Parse Transform for Parameter Modules"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://EPLICENCE;md5=f4e586d08cc73e9c7373939f6806d647"

SRCREV="7d51412387617b8f4635afa24f666826b93d81fc"
PR = "r4"

SRC_URI = "git://github.com/carosio/pmod_transform.git"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("pmod-transform", "pmod_transform*", "ebin", "Makefile EPLICENCE src include tests", d)
}

