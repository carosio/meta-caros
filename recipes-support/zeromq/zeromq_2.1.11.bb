DESCRIPTION = "ZeroMQ looks like an embeddable networking library but acts like a concurrency framework"
HOMEPAGE = "http://www.zeromq.org"
LICENSE = "LGPLv3+"
LIC_FILES_CHKSUM = "file://COPYING.LESSER;md5=66ea8704398d7996daeacd2fbd2b9dbd"

PR = "r2"

DEPENDS += "util-linux"

SRC_URI = "https://github.com/zeromq/zeromq2-x/archive/v${PV}.zip \
	   file://keepalive.patch"

S = "${WORKDIR}/zeromq2-x-2.1.11"

inherit autotools

SRC_URI[md5sum] = "c10aa4f6a6b2741eb66ab7b5259e32b7"
SRC_URI[sha256sum] = "615f714fea5b8701737f630b547dfe1edd26198c59073a195f8782779540bec5"
