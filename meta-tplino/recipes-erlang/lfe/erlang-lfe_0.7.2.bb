DESCRIPTION = "Lisp Flavored Erlang (LFE)"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

PR = "r1"

SRCREV = "fb7c96eb171df2599158fb52198451adfa6fd9ba"

SRC_URI = "https://github.com/rvirding/lfe/archive/${SRCREV}.tar.gz;downloadfilename=lfe-${SRCREV}.tar.gz"
SRC_URI[md5sum] = "cadc5c53982d6532344563e4e8a9b5eb"
SRC_URI[sha256sum] = "0cc8ad3307e1eb1ece289f4235e6b24bd9dcd48109fc774a7d17d1232172cd0f"

S = "${WORKDIR}/lfe-${SRCREV}"

TETRAPAK_OPTS += "-o build.version ${PV}~~fb7c96e"
TETRAPAK_OPTS += "-o package.maintainer 'Travelping GmbH <info@travelping.com>'"
TETRAPAK_OPTS += "-o package.exclude '^\.gitignore|^\.travis\.yml|^emacs|^Emakefile|^Makefile|^rebar\.config'"

inherit tetrapak

python () {
    erlang_def_package("lfe", "lfe*", "bin ebin", "doc examples include LICENSE README src test", d)
}
