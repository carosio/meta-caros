DESCRIPTION = "logging framework for Erlang"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ff253ad767462c46be284da12dda33e8"

SRCREV = "8d9ad6fbae6d1a7648164b9c5c9b181b17d017d5"
PR = "r1"

SRC_URI = "git://github.com/travelping/lager.git;protocol=git \
	   file://add-tetrapak.patch;apply=yes"

DEPENDS_append = " erlang-goldrush "
RDEPENDS_${PN}_append = " erlang-goldrush "

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("lager", "lager-*", "ebin", "include src test LICENSE README.md", d)
}
