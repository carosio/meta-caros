DESCRIPTION = "Parameterized Modules"
SECTION = "net"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://EPLICENCE;md5=f4e586d08cc73e9c7373939f6806d647"
PR = "r0"

SRC_URI = "git://git@git.tpip.net/pmod_transform;protocol=ssh"
SRCREV= "7d51412387617b8f4635afa24f666826b93d81fc"

S = "${WORKDIR}/git"

inherit tetrapaknative native

do_install() {
    install -d -m755 ${D}${libdir}/erlang/lib/pmod_transform
    cp -a ${S}/* ${D}${libdir}/erlang/lib/pmod_transform
}

