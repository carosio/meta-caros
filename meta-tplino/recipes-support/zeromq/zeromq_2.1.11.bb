DESCRIPTION = "ZeroMQ looks like an embeddable networking library but acts like a concurrency framework"
HOMEPAGE = "http://www.zeromq.org"
LICENSE = "LGPLv3+"
LIC_FILES_CHKSUM = "file://COPYING.LESSER;md5=66ea8704398d7996daeacd2fbd2b9dbd"

PR = "r0"

DEPENDS += "util-linux"

SRC_URI = "http://download.zeromq.org/zeromq-${PV}.tar.gz"

inherit autotools

SRC_URI[md5sum] = "f0f9fd62acb1f0869d7aa80379b1f6b7"
SRC_URI[sha256sum] = "e1cd4abbe353d3d72df42a2ee05593047915987024a4baf965745d29832eb214"
