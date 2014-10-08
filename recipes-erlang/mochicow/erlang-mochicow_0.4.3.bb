DESCRIPTION = "mochiweb adapter for cowboy"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1da7ca117f7008b0a376392e96337cde"

SRCREV = "2a87c60643c7966f8b1c968cbbce77d8ff8689d9"
PR = "r3"

SRC_URI = "https://github.com/ChicagoBoss/mochicow/archive/${SRCREV}.tar.gz;downloadfilename=mochicow-${SRCREV}.tar.gz"
SRC_URI[md5sum] = "364186976dce57fdba776b8ecd1f7805"
SRC_URI[sha256sum] = "30768f41d75ac85ca37ade2c243d6a88bc676584290f675bf804b734bcaf8589"

S = "${WORKDIR}/mochicow-${SRCREV}"

TETRAPAK_OPTS += "-o build.version ${PV}"
TETRAPAK_OPTS += "-o package.maintainer 'Travelping GmbH <info@travelping.com>'"
TETRAPAK_OPTS += "-o package.exclude '^\.gitignore|^LICENSE|^NOTICE|^rebar\.config'"

inherit tetrapak

DEPENDS += "erlang-mochiweb erlang-cowboy erlang-mimetypes erlang-pmod-transform"
RDEPENDS_${PN} += "erlang-mochiweb erlang-cowboy erlang-mimetypes"

python () {
    erlang_def_package("mochicow", "mochicow*", "ebin", "examples README.md src", d)
}
