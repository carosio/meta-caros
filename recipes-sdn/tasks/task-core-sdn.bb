DESCRIPTION = "Software Defined Networks (SDN) task for TPLINO"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PR = "r0"

PACKAGES = "\
    task-core-sdn \
    task-core-sdn-dbg \
    task-core-sdn-dev \
    "

ALLOW_EMPTY = "1"

RDEPENDS_task-core-sdn = "\
    erlang-flower \
    rofl-adpd \
    rofl-ethctld \
    rofl-ipctld"

RDEPENDS_task-core-sdn-dbg = "\
    erlang-flower-dbg \
    rofl-adpd-dbg \
    rofl-ethctld-dbg \
    rofl-ipctld-dbg"

RDEPENDS_task-core-sdn-dev = "\
    erlang-flower-dev \
    rofl-adpd-dev \
    rofl-ethctld-dev \
    rofl-ipctld-dev"

