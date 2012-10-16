DESCRIPTION = "Software Defined Networks (SDN) packagrgroup for TPLINO"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PR = "r0"

PACKAGES = "\
    packagegroup-core-sdn \
    packagrgroup-core-sdn-dbg \
    packagrgroup-core-sdn-dev \
    "

ALLOW_EMPTY = "1"

RDEPENDS_packagrgroup-core-sdn = "\
    erlang-flower \
    rofl-adpd \
    rofl-ethctld \
    rofl-ipctld \
    rofl-ipadaptd"

RDEPENDS_packagrgroup-core-sdn-dbg = "\
    erlang-flower-dbg"

RDEPENDS_packagrgroup-core-sdn-dev = "\
    erlang-flower-dev"
