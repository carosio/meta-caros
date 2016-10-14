SUMMARY = "Various tools relating to the Simple Network Management Protocol"
HOMEPAGE = "http://www.net-snmp.org/"
LICENSE = "BSD"

PR = "r2"

LIC_FILES_CHKSUM = "file://README;beginline=3;endline=8;md5=7f7f00ba639ac8e8deb5a622ea24634e"

DEPENDS = "openssl libnl pciutils"

SRC_URI = "${SOURCEFORGE_MIRROR}/net-snmp/net-snmp-${PV}.zip \
        file://0001-implement-partial-linux-network-namespace-support.patch \
        file://init \
        file://snmpd.conf \
        file://snmptrapd.conf \
        file://snmpd.service \
        file://snmptrapd.service \
        file://agentx-crash.patch \
        file://fix-request-id-0.patch \
        file://remove-build-host-includes.patch \
        file://environmentfile \
	file://fix-libnl-include-headers-detection.patch"

SRC_URI[md5sum] = "9f682bd70c717efdd9f15b686d07baee"
SRC_URI[sha256sum] = "e8dfc79b6539b71a6ff335746ce63d2da2239062ad41872fff4354cafed07a3e"

inherit autotools update-rc.d siteinfo systemd

EXTRA_OEMAKE = "INSTALL_PREFIX=${D}"

PARALLEL_MAKE = ""
CCACHE = ""

TARGET_CC_ARCH += "${LDFLAGS}"

EXTRA_OECONF = "--disable-embedded-perl \
                --with-perl-modules=no \
                --enable-shared \
                --disable-manuals \
                --with-defaults \
		--with-nl=${STAGING_INCDIR}/libnl3 \
                ${@base_conditional('SITEINFO_ENDIANNESS', 'le', '--with-endianness=little', '--with-endianness=big', d)}"

PACKAGECONFIG ?= "${@bb.utils.contains("DISTRO_FEATURES", "ipv6", "ipv6", "", d)}"
PACKAGECONFIG[ipv6] = "--enable-ipv6,--disable-ipv6,"

CACHED_CONFIGUREVARS = " \
    ac_cv_header_valgrind_valgrind_h=no \
    ac_cv_header_valgrind_memcheck_h=no \
"

do_configure_prepend() {
    export PERLPROG="${bindir}/env perl"
}

do_install_append() {
    install -d ${D}${sysconfdir}/snmp
    install -d ${D}${sysconfdir}/default
    install -d ${D}${sysconfdir}/snmp/config.d
    install -d ${D}${sysconfdir}/init.d
    install -m 755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/snmpd
    install -m 644 ${WORKDIR}/snmpd.conf ${D}${sysconfdir}/snmp/
    install -m 644 ${WORKDIR}/snmptrapd.conf ${D}${sysconfdir}/snmp/
    sed -e "s@-I/usr/include@@g" \
        -e "s@^prefix=.*@prefix=${STAGING_DIR_HOST}@g" \
        -e "s@^exec_prefix=.*@exec_prefix=${STAGING_DIR_HOST}@g" \
        -e "s@^includedir=.*@includedir=${STAGING_INCDIR}@g" \
        -e "s@^libdir=.*@libdir=${STAGING_LIBDIR}@g" \
        -i ${D}${bindir}/net-snmp-config
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/snmpd.service ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/snmptrapd.service ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/environmentfile ${D}${sysconfdir}/default/snmpd
}

SYSROOT_PREPROCESS_FUNCS += "net_snmp_sysroot_preprocess"

net_snmp_sysroot_preprocess () {
    if [ -e ${D}${bindir}/net-snmp-config ]; then
        install -d ${SYSROOT_DESTDIR}${bindir_crossscripts}/
        install -m 755 ${D}${bindir}/net-snmp-config ${SYSROOT_DESTDIR}${bindir_crossscripts}/
    fi
}

PACKAGES = "${PN}-dbg ${PN}-doc ${PN}-dev ${PN}-staticdev ${PN}-static ${PN}-libs \
            ${PN}-mibs ${PN}-server ${PN}-client ${PN}-server-snmpd ${PN}-server-snmptrapd"

ALLOW_EMPTY_${PN}-server = "1"

FILES_${PN}-libs = "${libdir}/lib*${SOLIBS}"
FILES_${PN}-mibs = "${datadir}/snmp/mibs"
FILES_${PN}-server-snmpd = "${sbindir}/snmpd \
                            ${sysconfdir}/snmp/snmpd.conf \
                            ${sysconfdir}/snmp/config.d \
                            ${sysconfdir}/init.d \
                            ${systemd_unitdir}/system/snmpd.service \
                            ${sysconfdir}/default/snmpd \
"

FILES_${PN}-server-snmptrapd = "${sbindir}/snmptrapd \
                                ${sysconfdir}/snmp/snmptrapd.conf \
                                ${systemd_unitdir}/system/snmptrapd.service \
"

FILES_${PN}-client = "${bindir}/* ${datadir}/snmp/"
FILES_${PN}-dbg += "${libdir}/.debug/ ${sbindir}/.debug/ ${bindir}/.debug/"
FILES_${PN}-dev += "${bindir}/net-snmp-config ${bindir}/mib2c ${bindir}/mib2c-update"

CONFFILES_${PN}-server-snmpd = "${sysconfdir}/snmp/snmpd.conf \
                                ${sysconfdir}/default/snmpd \
"
CONFFILES_${PN}-server-snmptrapd = "${sysconfdir}/snmp/snmptrapd.conf"

INITSCRIPT_PACKAGES = "${PN}-server"
INITSCRIPT_NAME_${PN}-server = "snmpd"
INITSCRIPT_PARAMS_${PN}-server = "defaults"

EXTRA_OECONF += "${@base_contains('DISTRO_FEATURES', 'systemd', '--with-systemd', '--without-systemd', d)}"

SYSTEMD_PACKAGES = "${PN}-server-snmpd \
                    ${PN}-server-snmptrapd"

SYSTEMD_SERVICE_${PN}-server-snmpd = "snmpd.service"
SYSTEMD_SERVICE_${PN}-server-snmptrapd =  "snmptrapd.service"

RDEPENDS_${PN}-server-snmpd += "net-snmp-mibs"
RDEPENDS_${PN}-server-snmptrapd += "net-snmp-server-snmpd"
RDEPENDS_${PN}-server += "net-snmp-server-snmpd net-snmp-server-snmptrapd"
RDEPENDS_${PN}-client += "net-snmp-mibs"
RDEPENDS_${PN}-dev = "net-snmp-client (= ${EXTENDPKGV}) net-snmp-server (= ${EXTENDPKGV})"
RRECOMMENDS_${PN}-dbg = "net-snmp-client (= ${EXTENDPKGV}) net-snmp-server (= ${EXTENDPKGV})"

RPROVIDES_${PN}-server-snmpd += "${PN}-server-snmpd-systemd"
RREPLACES_${PN}-server-snmpd += "${PN}-server-snmpd-systemd"
RCONFLICTS_${PN}-server-snmpd += "${PN}-server-snmpd-systemd"

RPROVIDES_${PN}-server-snmptrapd += "${PN}-server-snmptrapd-systemd"
RREPLACES_${PN}-server-snmptrapd += "${PN}-server-snmptrapd-systemd"
RCONFLICTS_${PN}-server-snmptrapd += "${PN}-server-snmptrapd-systemd"

LEAD_SONAME = "libnetsnmp.so"

pkg_postrm_${PN}-server() {
    if test "x$D" != "x"; then
        OPT="-r $D "
    else
        OPT=""
        /etc/init.d/snmpd stop
    fi
}
