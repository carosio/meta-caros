DESCRIPTION = "An erlang application for consuming, producing and manipulating json. inspired by yajl"
SECTION = "devel"
LICENSE = "other"
LIC_FILES_CHKSUM = "file://LICENSE;md5=65e442fe21115a1a7e2b2b8b1847476c"

PR = "r1"

SRC_URI = "https://s3.amazonaws.com/s3.hex.pm/tarballs/certifi-${PV}.tar;downloadfilename=certifi-${PV}.tar"
SRC_URI[md5sum] = "09970b56a4d27eb21f17ca94971207a2"
SRC_URI[sha256sum] = "42ae85fe91c038a634a5fb8d0c77f4fc581914c508f087c7138e9366a1517f6a"

S = "${WORKDIR}/certifi-${PV}"

inherit rebar
