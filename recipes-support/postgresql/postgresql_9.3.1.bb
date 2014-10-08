require postgresql.inc

LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=ab55a5887d3f8ba77d0fd7db787e4bab"

PR = "${INC_PR}.0"

SRC_URI += "file://remove.autoconf.version.check.patch"

SRC_URI[md5sum] = "c003d871f712d4d3895956b028a96e74"
SRC_URI[sha256sum] = "8ea4a7a92a6f5a79359b02e683ace335c5eb45dffe7f8a681a9ce82470a8a0b8"
