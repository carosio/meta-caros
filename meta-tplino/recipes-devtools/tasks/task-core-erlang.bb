DESCRIPTION = "Erlang task for TPLINO"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PR = "r0"

PACKAGES = "\
    task-core-erlang \
    task-core-erlang-dbg \
    task-core-erlang-dev \
    "

ALLOW_EMPTY = "1"

RDEPENDS_task-core-erlang = "erlang"
RDEPENDS_task-core-erlang-dbg = "erlang-dbg"
RDEPENDS_task-core-erlang-dev = "erlang-dev"
