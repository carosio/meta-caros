SUMMARY = "jaderl compiles the Jade template language to Erlang bytecode"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/jaderl.erl;md5=7a9e7abea03db1447e8f7f69595a5f56"

PR = "r2"

SRCREV = "14a80dafd92037d041dd352466fc3ddef5ae091e"

SRC_URI = "https://github.com/erlydtl/jaderl/archive/${SRCREV}.tar.gz;downloadfilename=jaderl-${SRCREV}.tar.gz"
SRC_URI[md5sum] = "b44bcca0b27befa8a23f2f5e71ee2617"
SRC_URI[sha256sum] = "786765c35c9d6cbf04d418245ef8a8bc4696c6987176a7103cb7375f58abfcc3"

S = "${WORKDIR}/jaderl-${SRCREV}"

TETRAPAK_OPTS += "-o build.version ${PV}"
TETRAPAK_OPTS += "-o package.maintainer 'Travelping GmbH <info@travelping.com>'"
TETRAPAK_OPTS += "-o package.exclude '^rebar|^rebar\.config|^spec\.odt|^tests'"

inherit tetrapak

DEPENDS += "erlang-dynamic-compile"
RDEPENDS_${PN} += "erlang-dynamic-compile"

python () {
    erlang_def_package("jaderl", "jaderl*", "ebin", "README.md src", d)
}
