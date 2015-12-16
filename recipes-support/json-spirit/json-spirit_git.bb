SUMMARY = "JSON Spirit"
DESCRIPTION = "JSON Spirit is a C++ JSON library using Boost Spirit for parsing."
HOMEPAGE = "https://github.com/sirikata/json-spirit"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=278ef6183dec4aae1524fccc4b0113c9"
SECTION = "console/tools"

SRC_URI = "git://github.com/sirikata/json-spirit.git"
SRCREV = "4f1a1023b5d14ec98887ead132d881b8a7acfd3c"
PV = "git${SRCPV}"
PR = "r1"

DEPENDS = "boost"

S = "${WORKDIR}/git"

OECMAKE_SOURCEPATH = "${S}/build"

inherit cmake

do_configure() {
        # Ensure we get the cmake configure and not qmake
        cd ${S}/build
        cmake_do_configure
}
