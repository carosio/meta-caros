DESCRIPTION = "An erlang application for consuming, producing and manipulating json. inspired by yajl"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b88dbbdb4895db36422fd59fca600660"

PR = "r1"

SRC_URI = "https://s3.amazonaws.com/s3.hex.pm/tarballs/jsx-${PV}.tar;downloadfilename=jsx-${PV}.tar"
SRC_URI[md5sum] = "1b906e79ac6bde7eba22d4684825a3a6"
SRC_URI[sha256sum] = "6bfccb6461cc3c7d5cc63f3e69ffeb2f1f8de50eca5980065311c056a69a907f"

S = "${WORKDIR}/jsx-${PV}"

inherit rebar
