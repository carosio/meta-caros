DESCRIPTION = "ABNF parser generator"
SECTION = "devel"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://src/abnfc.erl;beginline=2;endline=4;md5=ba49c52e6dc36b4509bef6848f420503"

SRCREV="e17f55958c9b02f71715426b02cf3b8e8d406851"
PR = "r1"
PV = "0.3+git${SRCPV}"

SRC_URI = "git://github.com/nox/abnfc.git;protocol=git \
	   file://add-tetrapak.patch;apply=yes"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("abnfc", "abnfc-*", "ebin priv", "bin src include samples", d)
}

FILES_${PN}-dev += "/usr/bin/abnfc"