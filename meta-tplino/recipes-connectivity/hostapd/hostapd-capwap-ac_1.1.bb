require hostapd_${PV}.bb

SRC_URI += " \
	file://hostapd-capwap.patch \
	file://hostapd_wtp.conf"

RCONFLICTS_${PN} = "hostapd hostapd-capwap-wtp"

RDEPENDS_${PN} += " opencapwap-ac "

CFLAGS += "-DLOCALUDP"

FILES_${PN} += " \
	${sbindir}/hostapd \
	${sbindir}/hostapd_cli \
	${sysconfdir}/hostapd.conf \
	${systemd_unitdir}/system/hostapd.service \
	${ROOT_HOME}/hostapd_ac.conf"

do_install_append() {
	install -m 0644 ${WORKDIR}/hostapd_ac.conf ${D}${ROOT_HOME}
}
