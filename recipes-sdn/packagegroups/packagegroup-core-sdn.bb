DESCRIPTION = "Software Defined Networks (SDN) packagegroup for CAROS"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PR = "r1"

inherit packagegroup

PACKAGE_GROUP_sdn = "${PN}"

RDEPENDS_${PN} = "\
    erlang-flower \
    rofl-adpd \
    rofl-ethctld \
    rofl-ipctld"

RDEPENDS_${PN}-dbg = "\
    erlang-flower-dbg"

RDEPENDS_${PN}-dev = "\
    erlang-flower-dev"
