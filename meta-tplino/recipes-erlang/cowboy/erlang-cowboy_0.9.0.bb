DESCRIPTION = "Small, fast, modular HTTP server."
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8a87a00323cbb9706ca2b35e5107b437"

PR = "r1"

SRCREV = "${PV}"

SRC_URI = "https://github.com/extend/cowboy/archive/${SRCREV}.tar.gz;downloadfilename=cowboy-${SRCREV}.tar.gz"
SRC_URI[md5sum] = "b7eb12646319f3f9f06ef5a068ee9d3a"
SRC_URI[sha256sum] = "21500ee93bee0dd4cfb80e0b42e9a336744fe4dd896495184df481d99e878b78"

S = "${WORKDIR}/cowboy-${SRCREV}"

TETRAPAK_OPTS += "-o build.version ${PV}"
TETRAPAK_OPTS += "-o package.maintainer 'Travelping GmbH <info@travelping.com>'"
TETRAPAK_OPTS += "-o package.exclude '^\.erlang\.mk\.packages\.v1|^\.gitignore|^\.rebar|^AUTHORS|^CHANGELOG\.md|^CONTRIBUTING\.md|^deps|^doc|^erlang\.mk|^guide|^LICENSE|^Makefile|^manual|^rebar\.config|^ROADMAP\.md'"

inherit tetrapak

DEPENDS +=  "erlang-ranch erlang-cowlib"
RDEPENDS_${PN} += "erlang-ranch erlang-cowlib"

python () {
    erlang_def_package("cowboy", "cowboy*", "ebin", "examples README.md src test", d)
}
