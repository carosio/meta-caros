SUMMARY = "Support library for manipulating Web protocols."
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=024593ecba065882390e825c2a022f33"

PR = "r1"

SRC_URI = "https://s3.amazonaws.com/s3.hex.pm/tarballs/cowlib-${PV}.tar;downloadfilename=cowlib.tar"
SRC_URI[md5sum] = "e343d1cca6c97f882772a704f559668a"
SRC_URI[sha256sum] = "db622da03aa039e6366ab953e31186cc8190d32905e33788a1acb22744e6abd2"

S = "${WORKDIR}/cowlib-${PV}"

DEPENDS += "erlang-native"

inherit rebar
