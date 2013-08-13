# Include buffalo-wzr-hp-ag300h specific platform configurations

SRC_URI_append_buffalo-wzr-hp-ag300h = " \
    file://settings.wtp.txt.buffalo-wzr-hp-ag300h \
    "

do_install_append_buffalo-wzr-hp-ag300h () {
    install -m 0644 ${WORKDIR}/settings.wtp.txt.buffalo-wzr-hp-ag300h ${D}${sysconfdir}/capwap/settings.wtp.txt
}
