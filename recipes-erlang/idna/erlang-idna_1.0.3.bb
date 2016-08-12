SUMMARY = "idna"
DESCRIPTION = "idna - HTTP client library in Erlang"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7c9b6269d40a09414db760aa524bf240"

PR = "r1"

SRC_URI = "https://s3.amazonaws.com/s3.hex.pm/tarballs/idna-${PV}.tar;downloadfilename=idna-${PV}.tar"
SRC_URI[md5sum] = "6ea706bd2f84ee95d7160135c92d5a1f"
SRC_URI[sha256sum] = "357d489a51112db4f216034406834f9172b3c0ff5a12f83fb28b25ca271541d1"

S = "${WORKDIR}/idna-${PV}"

inherit rebar
