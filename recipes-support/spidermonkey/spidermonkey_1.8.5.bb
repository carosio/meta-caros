SUMMARY = "Spidermonkey: a javascript engine written in C"
HOMEPAGE = "http://www.mozilla.org/js/spidermonkey/"
SECTION = "libs"

# the package is licensed under either of the following
LICENSE = "MPL-1 | GPLv2+ | LGPLv2.1+"
LIC_FILES_CHKSUM = "file://jsapi.cpp;beginline=4;endline=39;md5=347c6bbf4fb4547de1fa5ad830030063"
PR = "r2"
# r1 -> r2 for SECURITY_NO_PIE_CFLAGS / no other change

SRC_URI = "http://ftp.mozilla.org/pub/mozilla.org/js/js185-1.0.0.tar.gz \
	   file://no-neon-or-softfp-on-arm.patch;apply=yes;striplevel=3 \
	   file://fix-install_symlinks.patch;apply=yes;striplevel=3 \
	   file://fix-nspr-search-path.patch"

SRC_URI[md5sum] = "a4574365938222adca0a6bd33329cb32"
SRC_URI[sha256sum] = "5d12f7e1f5b4a99436685d97b9b7b75f094d33580227aa998c406bbae6f2a687"

S = "${WORKDIR}/js-1.8.5/js/src"

inherit autotools pkgconfig

DEPENDS += "nspr"
CONFLICTS = "js"

PARALLEL_MAKE = ""

BUILD_CXXFLAGS =+ "-I${STAGING_INCDIR}"

CACHED_CONFIGUREVARS += '\
    ac_cv_path_ZIP=/bin/true \
    HOST_CC="${BUILD_CC}" \
    HOST_CXX="${BUILD_CXX}" \
    HOST_LD="${BUILD_LD}" \
    HOST_RANLIB="${BUILD_RANLIB}" \
    HOST_AR="${BUILD_AR}" \
    HOST_CFLAGS="${BUILD_CFLAGS}" \
    HOST_CXXFLAGS="${BUILD_CXXFLAGS}" \
    HOST_LDFLAGS="${BUILD_LDFLAGS}" \
'

EXTRA_OECONF = '\
                --target=${TARGET_SYS} \
                --host=${BUILD_SYS} \
                --build=${BUILD_SYS} \
                --prefix=${prefix} \
                --libdir=${libdir} \
                --disable-tests \
                --disable-strip \
                --enable-threadsafe \
		--with-nspr-cflags="`pkg-config nspr --cflags`" \
		--with-nspr-libs="`pkg-config nspr --libs`" \
'

# we are only interested in the shared library, disable the
# standalone shell completely
#
# WARNING: enabling the shell will cause build failures!
#
EXTRA_OEMAKE="JS_DISABLE_SHELL=1 NO_EDITLINE=1"

do_configure() {
    # don't rebuild configure
    oe_runconf
}

do_install_append() {
    # We create a symlink in order to avoid failures of older packages that use -ljs
    ln -sf libmozjs185.so.1.0.0 ${D}${libdir}/libjs.so
    ln -sf libmozjs185.so ${D}${libdir}/libmozjs185-1.0.so
}

FILES_${PN} = "${libdir}/libmozjs185.so.1.0.0"

FILES_${PN}-dev = "${includedir} \
		   ${bindir}/js-config \
		   ${libdir}/pkgconfig \
		   ${libdir}/libmozjs185-1.0.so \
		   ${libdir}/libmozjs185.so \
		   ${libdir}/libmozjs185.so.1.0 \
		   ${libdir}/libjs.so"

FILES_${PN}-staticdev = "${libdir}/*.a"
