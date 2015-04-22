DESCRIPTION = "User space daemon for extended IEEE 802.11 management"
HOMEPAGE = "http://hostap.epitest.fi"
SECTION = "kernel/userland"

DEPENDS = "libnl openssl"

INC_PR = "r1"

DEFAULT_PREFERENCE = "-1"

SRC_URI = " \
	http://hostap.epitest.fi/releases/hostapd-${PV}.tar.gz \
	file://defconfig \
	file://hostapd.service"

FILES_${PN} += " ${systemd_unitdir}/system/hostapd.service "

S = "${WORKDIR}/hostapd-${PV}"

EXTRA_OEMAKE = "-C ${S}/hostapd"

inherit systemd

SYSTEMD_SERVICE_${PN} = "hostapd.service"

do_configure() {
	install -m 0644 ${WORKDIR}/defconfig ${S}/hostapd/.config
}

do_compile() {
	export CFLAGS="${CFLAGS} -MMD -Wall -g -I${STAGING_INCDIR}/libnl3"
	oe_runmake
}

do_install() {
	install -d ${D}${sbindir} \
		   ${D}${sysconfdir} \
		   ${D}${ROOT_HOME} \
		   ${D}${systemd_unitdir}/system

	install -m 0644 ${S}/hostapd/hostapd.conf ${D}${sysconfdir}
	install -m 0755 ${S}/hostapd/hostapd ${D}${sbindir}
	install -m 0755 ${S}/hostapd/hostapd_cli ${D}${sbindir}
	install -m 0644 ${WORKDIR}/hostapd.service ${D}${systemd_unitdir}/system
}

CONFFILES_${PN} += "${sysconfdir}/hostapd.conf"
