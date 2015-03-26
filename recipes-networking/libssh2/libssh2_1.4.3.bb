SUMMARY = "C library for writing portable SSH2 clients."
HOMEPAGE = "http://www.libssh2.org"
SECTION = "libs"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://COPYING;md5=d00afe44f336a79a2ca7e1681ce14509"

SRC_URI = "http://www.libssh2.org/download/libssh2-1.4.3.tar.gz"
SRC_URI[md5sum] = "071004c60c5d6f90354ad1b701013a0b"
SRC_URI[sha256sum] = "eac6f85f9df9db2e6386906a6227eb2cd7b3245739561cad7d6dc1d5d021b96d"

PR = "r1"

DEPENDS = "openssl"

inherit autotools

EXTRA_OECONF = "--disable-examples-build"
