require bind.inc

DEPENDS += "bind-native"

SRC_URI = "ftp://ftp.isc.org/isc/bind9/${PV}/${BPN}-${PV}.tar.gz \
           file://cross-build-fix.patch \
           file://fix-genrandom.patch \
           file://conf.patch \
           file://bind.service \
           file://rndc.key"

SRC_URI[md5sum] = "7baa8359f0773e04f63d7e694db1909c"
SRC_URI[sha256sum] = "e45c08217eb56eb35aa39f2bb2f6fa77ee626b4dcef165a1bf0f522e252fd6bc"

inherit systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "${PN}.service"

do_install_append() {
        rm "${D}${bindir}/nslookup"
        rm "${D}${mandir}/man1/nslookup.1"
        rm -rf "${D}/run" "${D}/var/run"
        install -d "${D}${sysconfdir}/bind" "${D}${systemd_unitdir}/system" "${D}/var/cache/bind"
        install -m 644 ${S}/conf/* "${D}${sysconfdir}/bind/"
        install -m 644 ${WORKDIR}/*.service "${D}${systemd_unitdir}/system/."
        install -m 644 ${WORKDIR}/rndc.key ${D}${sysconfdir}/bind/.
}

CONFFILES_${PN} = " \
        ${sysconfdir}/bind/named.conf \
        ${sysconfdir}/bind/named.conf.local \
        ${sysconfdir}/bind/named.conf.options \
        ${sysconfdir}/bind/db.0 \
        ${sysconfdir}/bind/db.127 \
        ${sysconfdir}/bind/db.empty \
        ${sysconfdir}/bind/db.local \
        ${sysconfdir}/bind/db.root"
