SUMMARY = "Socket acceptor pool for TCP protocols."
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8445cb073d1f4438d5975bde2f16aa27"

PR = "r1"

SRC_URI = "https://s3.amazonaws.com/s3.hex.pm/tarballs/ranch-${PV}.tar;downloadfilename=ranch-${PV}.tar"
SRC_URI[md5sum] = "130bc16a6e1af51b235096594779f318"
SRC_URI[sha256sum] = "f602d057615ce737945c239e9c8155d3f5300fc5b1255abf81f2a9d0d08e5b04"

S = "${WORKDIR}/ranch-${PV}"

inherit rebar
