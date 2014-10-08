DESCRIPTION = "Library for including a Cisco-like command-line interface into other software."
HOMEPAGE = "https://github.com/dparrish/libcli"
SECTION = "libs"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fad9b3332be894bab9bc501572864b29"

SRCREV="e33c1bbc04bb4469547509871b6d9b094d9f6264"
PR = "r0"

SRC_URI = "git://github.com/dparrish/libcli.git;protocol=git"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = "'PREFIX=${prefix}'"

do_install() {
        oe_runmake install DESTDIR="${D}"
}
