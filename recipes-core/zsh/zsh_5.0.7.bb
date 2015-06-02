SUMMARY = "UNIX Shell similar to the Korn shell"
DESCRIPTION = "Zsh is a shell designed for interactive use, although it is also a \
               powerful scripting language. Many of the useful features of bash, \
               ksh, and tcsh were incorporated into zsh; many original features were added."
HOMEPAGE = "http://www.zsh.org"
SECTION = "base/shell"

LICENSE = "zsh"
LIC_FILES_CHKSUM = "file://LICENCE;md5=b7bc853894664be455a922db9805288e"

PR = "r5"

SRC_URI = "${SOURCEFORGE_MIRROR}/${BPN}/${BP}.tar.gz \
    file://profile_d_zshfix \
    file://zprofile \
"

SRC_URI[md5sum] = "76726ff50309e628de670476e0508b3a"
SRC_URI[sha256sum] = "43f0a4c179ef79bb8c9153575685f7f45f28a3615c8cf96345f503d5b9e7b919"

DEPENDS = "ncurses bison-native libcap libpcre gdbm groff-native"

inherit autotools gettext update-alternatives

EXTRA_OECONF = " \
    --bindir=${base_bindir} \
    --enable-etcdir=${sysconfdir} \
    --enable-fndir=${datadir}/${PN}/${PV}/functions \
    --enable-site-fndir=${datadir}/${PN}/site-functions \
    --with-term-lib='ncursesw ncurses' \
    --with-tcsetpgrp \
    --enable-cap \
    --enable-multibyte \
    --disable-gdbm \
    --disable-dynamic \
    zsh_cv_shared_environ=yes \
"

ALTERNATIVE_${PN} = "sh"
ALTERNATIVE_LINK_NAME[sh] = "${base_bindir}/sh"
ALTERNATIVE_TARGET[sh] = "${base_bindir}/${BPN}"
ALTERNATIVE_PRIORITY = "100"

CONFFILES_${PN} = "${sysconfdir}/zprofile"

export AUTOHEADER = "true"

do_configure () {
    gnu-configize --force ${S}
    oe_runconf
}


do_install_append () {
    rm -fr ${D}/usr/share
    install -d -o 0 -g 0 -m 0755 ${D}${sysconfdir}/profile.d/
    install -o 0 -g 0 -m 0444 ${WORKDIR}/profile_d_zshfix ${D}${sysconfdir}/profile.d/zshfix
    install -o 0 -g 0 -m 0444 ${WORKDIR}/zprofile ${D}${sysconfdir}/zprofile
}

pkg_postinst_${PN} () {
    test -e || touch $D${sysconfdir}/shells
    grep -q "bin/zsh" $D${sysconfdir}/shells || echo /bin/zsh >> $D${sysconfdir}/shells
    grep -q "bin/sh" $D${sysconfdir}/shells || echo /bin/sh >> $D${sysconfdir}/shells
}

FILES_${PN}-dbg += "\
    ${libdir}/${PN}/${PV}/${PN}/.debug/*.so \
    ${libdir}/${PN}/${PV}/${PN}/db/.debug/*.so \
    ${libdir}/${PN}/${PV}/${PN}/net/.debug/*.so \
"
