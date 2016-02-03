SUMMARY = "logging framework for Erlang"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ff253ad767462c46be284da12dda33e8"

PR = "r1"

SRC_URI = "https://s3.amazonaws.com/s3.hex.pm/tarballs/lager-${PV}.tar;downloadfilename=lager-${PV}.tar \
           file://fix-boss_compiler.patch;apply=yes"
SRC_URI[md5sum] = "2310c1c075d2cd958bda5bb7a2d17c2c"
SRC_URI[sha256sum] = "5eb1c17ff0f8692285b7648ef5d827d492b8d7554da782afc300ebb4861d5aba"

DEPENDS += "erlang-goldrush"
RDEPENDS_${PN} += "erlang-goldrush"

S = "${WORKDIR}/lager-${PV}"

inherit rebar
