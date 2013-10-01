DESCRIPTION = "ErlyDTL compiles the Django Template Language to Erlang bytecode"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.markdown;md5=0798b8144f0dd94b98eeed1727451a2d"

SRCREV="4b6b2494b2121aaeacf16e329c1d3992b15c6eea"
PR = "r1"

SRC_URI = "git://git@git.tpip.net/erlydtl.git;protocol=ssh"

S = "${WORKDIR}/git"

inherit tetrapak

DEPENDS_append = " erlang-gettext "
RDEPENDS_${PN}_append = " erlang-gettext "

python () {
    erlang_def_package("erlydtl", "erlydtl*", "bin ebin priv", "src include", d)
}

