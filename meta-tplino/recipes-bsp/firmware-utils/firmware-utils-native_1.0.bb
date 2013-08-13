DESCRIPTION = "Fireware Utils"
LICENSE = "GPL-2+"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE;md5=94d55d512a9ba36caa9b7df079bae19f"

DEPENDS = "zlib-native"

PR = "r1"

SRC_URI = "file://LICENSE \
	   file://src"

S = "${WORKDIR}/src"

inherit native

do_configure () {
}

do_compile () {
	oe_runmake all
}

do_install () {
	install -d ${D}${bindir}
	install -m 0755 ${S}/build/* ${D}${bindir}
}