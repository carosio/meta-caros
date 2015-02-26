DESCRIPTION = "High-level C binding for ZeroMQ"
HOMEPAGE = "http://czmq.zeromq.org/"
SECTION = "devel"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9741c346eef56131163e13b9db1241b3"

PR = "r0.2"

DEPENDS += "zeromq"
SRC_URI = "https://github.com/zeromq/czmq/archive/v${PV}.tar.gz;downloadfilename=czmq-${PV}.tar.gz \
	file://default-source.patch \
	file://legacy-zmq-compat.patch"
SRC_URI[md5sum] = "1c45eeac1377c50244b1847fa1d6a419"
SRC_URI[sha256sum] = "3c95aab7434ac0a074a46217122c9f454c36befcd0b5aaa1f463aae0838dd499"

inherit pkgconfig autotools
