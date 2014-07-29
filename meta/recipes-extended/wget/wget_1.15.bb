DESCRIPTION = "A console URL download utility featuring HTTP, FTP, and more."
SECTION = "console/network"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

DEPENDS = "openssl"

SRC_URI = "${GNU_MIRROR}/wget/wget-${PV}.tar.gz \
           file://fix_makefile.patch"

SRC_URI[md5sum] = "506df41295afc6486662cc47470b4618"
SRC_URI[sha256sum] = "52126be8cf1bddd7536886e74c053ad7d0ed2aa89b4b630f76785bac21695fcd"

PR = "r1"

inherit autotools gettext update-alternatives

EXTRA_OECONF = "--with-libc --enable-ipv6 --with-libssl-prefix=${STAGING_DIR_HOST} \
                --with-ssl=openssl --disable-rpath --disable-iri"

CACHED_CONFIGUREVARS += 'ac_cv_header_pcre_h=no'

ALTERNATIVE_${PN} = "wget"
ALTERNATIVE_PRIORITY = "100"

FILES_${PN} += "${sysconfdir}/wgetrc"
CONFFILES_${PN} += "${sysconfdir}/wgetrc"
