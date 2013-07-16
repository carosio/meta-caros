require openssl.inc

# For target side versions of openssl enable support for OCF Linux driver
# if they are available.
DEPENDS += "ocf-linux"

CFLAG += "-DHAVE_CRYPTODEV -DUSE_CRYPTODEV_DIGESTS"

PR = "${INC_PR}.1"

LIC_FILES_CHKSUM = "file://LICENSE;md5=f9a8f968107345e0b75aa8c2ecaa7ec8"

export DIRS = "crypto ssl apps engines"
export OE_LDFLAGS="${LDFLAGS}"

SRC_URI += "file://configure-targets.patch \
            file://shared-libs.patch \
            file://oe-ldflags.patch \
            file://engines-install-in-libdir-ssl.patch \
            file://openssl-fix-link.patch \
            file://debian/version-script.patch \
            file://debian/pic.patch \
            file://debian/c_rehash-compat.patch \
            file://debian/ca.patch \
            file://debian/make-targets.patch \
            file://debian/no-rpath.patch \
            file://debian/man-dir.patch \
            file://debian/man-section.patch \
            file://debian/no-symbolic.patch \
            file://debian/debian-targets.patch \
            file://openssl_fix_for_x32.patch \
            file://openssl-dtls-mtu.patch \
            file://find.pl \
           "

SRC_URI[md5sum] = "99af9b319f928da5ea3e860311b396ef"
SRC_URI[sha256sum] = "2982b2e9697a857b336c5c1b1b7b463747e5c1d560f25f6ace95365791b1efd1"

PACKAGES =+ " \
	${PN}-engines \
	${PN}-engines-dbg \
	"

FILES_${PN}-engines = "${libdir}/ssl/engines/*.so ${libdir}/engines"
FILES_${PN}-engines-dbg = "${libdir}/ssl/engines/.debug"

PARALLEL_MAKEINST = ""

# source tree contain dangling symlinks
openssl_do_patch() {
  cd ${S}
  rm -f include/openssl/tmdiff.h include/openssl/srtp.h include/openssl/fips.h include/openssl/cmac.h include/openssl/pq_compat.h include/openssl/srp.h include/openssl/fips_rand.h
}

# We invoke base do_patch at end, to incorporate any local patch
python do_patch() {
    bb.build.exec_func('patch_do_patch', d)
    bb.build.exec_func('openssl_do_patch', d)
}

do_configure_prepend() {
  cp ${WORKDIR}/find.pl ${S}/util/find.pl
}
