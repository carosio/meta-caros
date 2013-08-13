# Include  specific buffalo-wzr-hp-ag300h platform configurations

SRC_URI_append_buffalo-wzr-hp-ag300h = " \
    file://hostapd.conf.buffalo-wzr-hp-ag300h \
"

do_install_append_buffalo-wzr-hp-ag300h () {
    install -m 0644 ${WORKDIR}/hostapd.conf.buffalo-wzr-hp-ag300h ${D}${sysconfdir}/hostapd.conf
}
