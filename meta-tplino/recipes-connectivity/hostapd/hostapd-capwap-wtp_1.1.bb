require hostapd_${PV}.bb

SRC_URI += " \
	file://hostapd-capwap.patch \
	file://hostapd_wtp.conf"

RCONFLICTS_${PN} = "hostapd hostapd-capwap-ac"

RDEPENDS_${PN} += " opencapwap-wtp "

CFLAGS += "-DLOCALUDP"

FILES_${PN} += " \
	${sbindir}/hostapd \
	${sbindir}/hostapd_cli \
	${sysconfdir}/hostapd.conf \
	${ROOT_HOME}/hostapd_wtp.conf"

do_install_append() {
  install -d ${D}${ROOT_HOME} 
	install -m 0644 ${WORKDIR}/hostapd_wtp.conf ${D}${ROOT_HOME}/hostapd_wtp.conf
}
