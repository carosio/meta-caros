DESCRIPTION = "Software Defined Networks (SDN) packagrgroups for TPLINO"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PR = "r0"

ALLOW_EMPTY = "1"

PACKAGES = "\
    packagrgroup-core-sdn-sdk \
    packagrgroup-core-sdn-sdk-dbg \
    packagrgroup-core-sdn-sdk-dev \
    "

ALLOW_EMPTY = "1"

RDEPENDS_packagrgroup-core-sdn-sdk = "\
    erlang-flower \
    rofl \
    rofl-adpd \
    rofl-ethctld \
    rofl-ipctld \
    rofl-ipadaptd"
