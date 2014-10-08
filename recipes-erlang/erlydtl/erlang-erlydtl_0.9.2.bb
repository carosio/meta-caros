DESCRIPTION = "Django Template Language for Erlang"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/erlydtl.erl;md5=bcd0b6dc79c62cb6823e0e073ac08482"

PR = "r1"

SRCREV = "b9ec861c2dd461d5121cca96688f5228b2c30ecb"

SRC_URI = "https://github.com/erlydtl/erlydtl/archive/${SRCREV}.tar.gz;downloadfilename=erlydtl-${SRCREV}.tar.gz \
           file://config.ini"
SRC_URI[md5sum] = "73a3909261e9b36f197f797a7cb8124c"
SRC_URI[sha256sum] = "997e62d4c0505082e7ed85c4799cc5ad2f4e95ba23ec3fe8967fcbb160df77f0"

S = "${WORKDIR}/erlydtl-${SRCREV}"

TETRAPAK_OPTS += "-o build.version ${PV}"
TETRAPAK_OPTS += "-o package.maintainer 'Travelping GmbH <info@travelping.com>'"
TETRAPAK_OPTS += "-o package.exclude '^\.gitignore|^\.travis\.yml|^bin|^CONTRIBUTING\.md|^Makefile|^README\.markdown|^README_I18N|^rebar|^rebar-slex\.config|^rebar\.config'"

inherit tetrapak

DEPENDS += "erlang-merl erlang-gettext"

addtask do_prepare_compile after do_patch before do_compile

do_prepare_compile() {
    # add tetrapak
    mkdir -p ${S}/tetrapak
    cp ${WORKDIR}/config.ini ${S}/tetrapak
}

python () {
    erlang_def_package("erlydtl", "erlydtl*", "bin ebin priv", "include NEWS.md src test", d)
}
