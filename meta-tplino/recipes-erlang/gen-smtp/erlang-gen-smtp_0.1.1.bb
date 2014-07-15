DESCRIPTION = "An erlang SMTP server/client framework"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2836c20a9a9293da518007b3786dbcfb"

PR = "r2"

SRCREV = "fd0426c46468c33c21332335c267ae6e13e01afb"

SRC_URI = "https://github.com/Vagabond/gen_smtp/archive/${SRCREV}.tar.gz;downloadfilename=gen_smtp-${SRCREV}.tar.gz"
SRC_URI[md5sum] = "3ec4f24e8baf317ca994cc13141ec581"
SRC_URI[sha256sum] = "46ed13bb72ffaaab5fdec67d8aefa56fb22ec034b80bfdad373ecd0610824417"

S = "${WORKDIR}/gen_smtp-${SRCREV}"

TETRAPAK_OPTS += "-o build.version ${PV}~~fd0426c"
TETRAPAK_OPTS += "-o package.maintainer 'Travelping GmbH <info@travelping.com>'"
TETRAPAK_OPTS += "-o package.exclude '^\.gitignore|^\.travis\.yml|^Emakefile|^Makefile|^README\.markdown|^rebar|^rebar\.config|^rebar\.test\.config|^testdata'"

inherit tetrapak

DEPENDS += "erlang-iconv"
RDEPENDS_${PN} += "erlang-iconv"

python () {
    erlang_def_package("gen-smtp", "gen_smtp*", "ebin", "LICENSE src test", d)
}
