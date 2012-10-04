DESCRIPTION = "Software Defined Networks (SDN) tasks for TPLINO"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PR = "r0"

ALLOW_EMPTY = "1"

PACKAGES = "\
    task-core-sdn-sdk \
    task-core-sdn-sdk-dbg \
    task-core-sdn-sdk-dev \
    "

ALLOW_EMPTY = "1"

RDEPENDS_task-core-sdn-sdk = "\
    erlang-flower \
    rofl-adpd \
    rofl-ethctld \
    rofl-ipctld \
    rofl-ipadaptd"
