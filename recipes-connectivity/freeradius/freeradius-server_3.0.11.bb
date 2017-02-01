SUMMARY = "FreeRADIUS Server"
HOMEPAGE = "http://wiki.freeradius.org"
LICENSE = "GPLv2"

PR = "r4"

LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=8271badacbbc8d1e3c62027d15cd176d"

SRC_URI = "ftp://ftp.freeradius.org/pub/freeradius/old/${PN}-${PV}.tar.bz2"
SRC_URI += "file://0001-Fix-cross-compile.patch"
SRC_URI += "file://freeradius.service"

SRC_URI[md5sum] = "e8a748fe824223ed488cc74a1645023e"
SRC_URI[sha256sum] = "2b6109b61fc93e9fcdd3dd8a91c3abbf0ce8232244d1d214d71a4e5b7faadb80"

DEPENDS = "gdbm libtalloc libpcre libpcap openssl"

EXTRA_OECONF += " --with-pcre-include-dir=${STAGING_INCDIR} "
EXTRA_OECONF += " --libdir=${libdir}/freeradius "
EXTRA_OECONF += " --disable-static "

CACHED_CONFIGUREVARS = "ax_cv_cc_builtin_choose_expr=yes \
ax_cv_cc_builtin_types_compatible_p=yes \
ax_cv_cc_builtin_bswap64=yes \
ax_cv_cc_bounded_attribute=yes \
ac_cv_lib_collectdclient_lcc_connect=no \
ac_cv_lib_execinfo_backtrace_symbols=yes \
"

do_configure_prepend() {
    rm -rf ${B}/.pc
}

do_configure_append() {
    cd ${B}
    touch -t 197001010000 ${B}/src/include/features.h
    make reconfig

    parentdir=`pwd`
    mysubdirs="$mysubdirs `find src/modules/ -name configure.ac -print |egrep -v 'rlm_(ruby|perl|python|krb5|sql)' | sed 's%/configure.ac%%'`"
    mysubdirs=`echo $mysubdirs`

    for F in $mysubdirs
    do
            echo "Configuring in $F..."
            (cd $F && grep "^AC_CONFIG_HEADER" configure.ac > /dev/null || exit 0; autoheader -I$parentdir)
            (cd $F && autoconf -I$parentdir)
            (cd $F && ./configure ${CONFIGUREOPTS} ${EXTRA_OECONF} )
    done

}

do_install_append() {
	install -d ${D}/${datadir}
	install -d ${D}/${datadir}/freeradius
	cp ${B}/share/dict* ${D}${datadir}/freeradius

	install -d ${D}/${sysconfdir}
	install -d ${D}/${sysconfdir}/freeradius
	cp -a ${B}/raddb/* ${D}${sysconfdir}/freeradius
	rm ${D}${sysconfdir}/freeradius/*.conf.in
	rm ${D}${sysconfdir}/freeradius/all.mk

	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/freeradius.service ${D}${systemd_unitdir}/system
}

FILES_${PN} += "${datadir}"
FILES_${PN} += "${libdir}/freeradius"
FILES_${PN} += "${sysconfdir}/freeradius"
FILES_${PN}-staticdev += "${libdir}/freeradius/*.a"
FILES_${PN}-dbg += "${libdir}/freeradius/.debug"

CONFFILES_${PN} += "${sysconfdir}/freeradius/dictionary"
CONFFILES_${PN} += "${sysconfdir}/freeradius/sites-available/*"
CONFFILES_${PN} += "${sysconfdir}/freeradius/policy.d/*"
CONFFILES_${PN} += "${sysconfdir}/freeradius/mods-available/*"
CONFFILES_${PN} += "${sysconfdir}/freeradius/mods-config/*"
CONFFILES_${PN} += "${sysconfdir}/freeradius/*.conf"
CONFFILES_${PN} += "${sysconfdir}/freeradius/certs/*.cnf"

inherit autotools-brokensep systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "freeradius.service"

