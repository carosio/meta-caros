SUMMARY = "Small, fast, modular HTTP server."
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7dda94e1b8b8a787d2c02b6bf81ee2e2"

PR = "r1"

SRC_URI = "https://s3.amazonaws.com/s3.hex.pm/tarballs/cowboy-${PV}.tar;downloadfilename=cowboy-${PV}.tar"
SRC_URI[md5sum] = "bb849706beaae0aa33ef154142c67c12"
SRC_URI[sha256sum] = "6a0edee96885fae3a8dd0ac1f333538a42e807db638a9453064ccfdaa6b9fdac"

S = "${WORKDIR}/cowboy-${PV}"

inherit rebar

DEPENDS +=  "erlang-ranch erlang-cowlib"
RDEPENDS_${PN} +=  "erlang-ranch erlang-cowlib"
