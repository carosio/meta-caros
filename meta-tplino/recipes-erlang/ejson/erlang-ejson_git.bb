DESCRIPTION = "EJSON - decode and encode JSON into/from Erlang terms"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=819a7baf87a425c2d31a198a87803484"

SRCREV="3b81c6a55ac6c0affcf063610c9b8eb0e4468a11"
PV = "0.1.6+git${SRCPV}"
PR = "r1"

SRC_URI = "git://github.com/benoitc/ejson.git;protocol=git \
	   file://add-tetrapak.patch;apply=yes"

S = "${WORKDIR}/git"

inherit tetrapak

DEPENDS += "erlang-mochiweb"
RDEPENDS_${PN} += "erlang-mochiweb"

python () {
    erlang_def_package("ejson", "ejson-*", "ebin priv", "c_src src include t LICENSE", d)
}
