DESCRIPTION = "Software Defined Networks (SDN) packagegroup for TPLINO"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PR = "r0"

inherit packagegroup

PACKAGE_GROUP_sdn = "packagegroup-core-sdn"

PACKAGES = "\
    packagegroup-core-sdn \
    packagegroup-core-sdn-dbg \
    packagegroup-core-sdn-dev \
    "

RDEPENDS_packagegroup-core-sdn = "\
    erlang-flower \
    rofl-adpd \
    rofl-ethctld \
    rofl-ipctld"

RDEPENDS_packagegroup-core-sdn-dbg = "\
    erlang-flower-dbg"

RDEPENDS_packagegroup-core-sdn-dev = "\
    erlang-flower-dev"
