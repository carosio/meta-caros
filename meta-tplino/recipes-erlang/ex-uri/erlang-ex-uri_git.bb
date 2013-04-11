DESCRIPTION = "An URI-handling library application"
SECTION = "devel"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8b3666f954af52296686a28bc5f337f0"

SRCREV="7c3b320a0e5449fa1225a6ec18e628f9a3a88053"
PV = "1.0+git${SRCPV}"
PR = "r2"

SRC_URI = "git://github.com/extend/ex_uri.git;protocol=git \
	   file://add-tetrapak.patch;apply=yes"

S = "${WORKDIR}/git"

DEPENDS += "erlang-abnfc"
RDEPENDS_${PN} += "erlang-abnfc"

inherit tetrapak

python () {
    erlang_def_package("ex-uri", "ex_uri-*", "ebin priv", "src include README LICENSE", d)
}
