DESCRIPTION = "GNU libmicrohttpd is a small C library that is supposed to make it easy to run an HTTP server as part of another application."
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=9331186f4f80db7da0e724bdd6554ee5"

DEPENDS = "libgcrypt curl"

SRC_URI = "http://ftp.gnu.org/gnu/libmicrohttpd/libmicrohttpd-${PV}.tar.gz"
SRC_URI[md5sum] = "86c9cb728b4bd4fcb8aeca18ba6c0548"
SRC_URI[sha256sum] = "4f937b6065c366d776be86b1d24b8fc400ebc7ea006a9d77c49a8f2f0cd7e373"

inherit autotools lib_package

do_compile_append() {
	sed -i s:-L${STAGING_LIBDIR}::g libmicrohttpd.pc
}
