DESCRIPTION = "MochiMedia Web Server"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5e0144b1f4eb0e6001053e5e37e97db2"

PR = "r1"

SRCREV_ORIG = "${PV}"
SRCREV = "v${SRCREV_ORIG}"

SRC_URI = "https://github.com/mochi/mochiweb/archive/${SRCREV}.tar.gz;downloadfilename=mochiweb-${SRCREV_ORIG}.tar.gz"
SRC_URI[md5sum] = "0ff8d5dca3270b7dfb57ad6a58018e27"
SRC_URI[sha256sum] = "6eae3c5a1d19029b10d92b0f37b402cb319c93ea636e7f9f34dc2f2a0cdc72bd"

S = "${WORKDIR}/mochiweb-${SRCREV_ORIG}"

TETRAPAK_OPTS += "-o build.version ${PV}"
TETRAPAK_OPTS += "-o package.maintainer 'Travelping GmbH <info@travelping.com>'"
TETRAPAK_OPTS += "-o package.exclude '^\.gitignore|^\.travis\.yml|^CHANGES\.md|^LICENSE|^Makefile|^README|^rebar|^rebar\.config|^scripts|^support'"

inherit tetrapak

python () {
    erlang_def_package("mochiweb", "mochiweb*", "ebin", "examples include src test", d)
}
