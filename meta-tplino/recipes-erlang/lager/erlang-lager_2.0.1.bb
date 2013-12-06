DESCRIPTION = "logging framework for Erlang"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ff253ad767462c46be284da12dda33e8"

SRCREV = "578a2d1e07b4ad6e2b280fcc4a11fcc1d479878e"
PR = "r1"

SRC_URI = "git://github.com/travelping/lager.git;protocol=git;branch=add_journald \
           file://add-tetrapak.patch;apply=yes"

DEPENDS_append = " erlang-goldrush erlang-ejournald "
RDEPENDS_${PN}_append = " erlang-goldrush erlang-ejournald "

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("lager", "lager-*", "ebin", "include src test LICENSE README.md", d)
}
