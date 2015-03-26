SUMMARY = "Socket acceptor pool for TCP protocols."
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=09d6d59d144d8f52d9fe5c1cc8f47f0f"

PR = "r1"

SRCREV = "${PV}"

SRC_URI = "https://github.com/extend/ranch/archive/${SRCREV}.tar.gz;downloadfilename=ranch-${SRCREV}.tar.gz"
SRC_URI[md5sum] = "ba259f63eb6588463738db5bd3208fee"
SRC_URI[sha256sum] = "136ade01926b823f320d16ce24b1bddc7ff914d42d4774fc2f1392a42fee8076"

S = "${WORKDIR}/ranch-${SRCREV}"

TETRAPAK_OPTS += "-o build.version ${PV}"
TETRAPAK_OPTS += "-o package.maintainer 'Travelping GmbH <info@travelping.com>'"
TETRAPAK_OPTS += "-o package.exclude '^\.gitignore|^AUTHORS|^doc|^erlang\.mk|^guide|^LICENSE|^Makefile|^ROADMAP\.md'"

inherit tetrapak

python () {
    erlang_def_package("ranch", "ranch*", "ebin", "examples README.md src test", d)
}
