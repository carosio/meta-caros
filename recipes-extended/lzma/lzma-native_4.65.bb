SUMMARY = "utils for managing LZMA compressed files"
SECTION = "base"

LICENSE = "PD GPLv2+"
LIC_FILES_CHKSUM = "file://lzma.txt;beginline=18;endline=21;md5=3aaef491e9d8c85443a63fec4bc08890 \
		    file://CPP/7zip/Compress/LZMA_Alone/lzmp.cpp;endline=20;md5=702d5abb4b4de26f7ba269810f3c2819"

SRC_URI = "http://downloads.openwrt.org/sources/lzma-${PV}.tar.bz2 \
	   file://001-large_files.patch \
	   file://002-lzmp.patch \
	   file://003-compile_fixes.patch \
	   file://100-static_library.patch"
SRC_URI[md5sum] = "434e51a018b4c8ef377bf81520a53af0"
SRC_URI[sha256sum] = "dcbdb5f4843eff638e4a5e8be0e2486a3c5483df73c70823618db8e66f609ec2"

DEPENDS="xz-native"

inherit native

UTIL_DIR = "${S}/C/LzmaUtil"
ALONE_DIR = "${S}/CPP/7zip/Compress/LZMA_Alone"

do_configure () {
}

do_compile () {
    oe_runmake -C ${UTIL_DIR} -f makefile.gcc 'LDFLAGS=${LDFLAGS} -s' 'CFLAGS=${CFLAGS} -c -O2 -Wall'
    oe_runmake -C ${ALONE_DIR} -f makefile.gcc 'LDFLAGS=${LDFLAGS} -s' 'CFLAGS=${CFLAGS} -c -I ../../../ -D_FILE_OFFSET_BITS=64 -DPACKAGE_VERSION="\"${PV}\""'
}

do_install () {
    mkdir -p ${D}${bindir}
    install -m 0755 ${S}/CPP/7zip/Compress/LZMA_Alone/lzma_alone ${D}${bindir}/lzma1
}