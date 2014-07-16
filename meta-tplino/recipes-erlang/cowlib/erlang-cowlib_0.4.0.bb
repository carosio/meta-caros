DESCRIPTION = "Support library for manipulating Web protocols."
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=797dcce8eb39b26403101f234ad9c7e2"

PR = "r1"

SRCREV = "${PV}"

SRC_URI = "https://github.com/extend/cowlib/archive/${SRCREV}.tar.gz;downloadfilename=cowlib-${SRCREV}.tar.gz"
SRC_URI[md5sum] = "43f2bd28c5f6cf7a566e6086a50a0952"
SRC_URI[sha256sum] = "0bcab56705e12eeb1871e13c84bfac8f2abd51b76a7e6d27f6939129c56056be"

S = "${WORKDIR}/cowlib-${SRCREV}"

TETRAPAK_OPTS += "-o build.version ${PV}"
TETRAPAK_OPTS += "-o package.maintainer 'Travelping GmbH <info@travelping.com>'"
TETRAPAK_OPTS += "-o package.exclude '^erlang\.mk|^LICENSE|^Makefile'"

inherit tetrapak

python () {
    erlang_def_package("cowlib", "cowlib*", "ebin", "README.md src test", d)
}
