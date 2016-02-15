SUMMARY = "A mocking library for Erlang"
SECTION = "devel"
LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=ff253ad767462c46be284da12dda33e8"

PR = "r1"

SRC_URI = "https://s3.amazonaws.com/s3.hex.pm/tarballs/meck-${PV}.tar"
SRC_URI[md5sum] = "f3f883300da50872e2629880a9136e11"
SRC_URI[sha256sum] = "53bd3873d0193d6b2b4a165cfc4b9ffc3934355c3ba19e88239ef6a027cc02b6"

S = "${WORKDIR}/meck-${PV}"

inherit rebar
