DESCRIPTION = "An erlang application for consuming, producing and manipulating json. inspired by yajl"
SECTION = "devel"
LICENSE = "other"
LIC_FILES_CHKSUM = "file://LICENSE;md5=14581c3b928971816d192b88541f693a"

PR = "r1"

SRC_URI = "https://s3.amazonaws.com/s3.hex.pm/tarballs/mimerl-${PV}.tar;downloadfilename=mimerl-${PV}.tar"
SRC_URI[md5sum] = "2df7023b2e4b77eb9e7d1341f8189e3b"
SRC_URI[sha256sum] = "7a4c8e1115a2732a67d7624e28cf6c9f30c66711a9e92928e745c255887ba465"

S = "${WORKDIR}/mimerl-${PV}"

inherit rebar
