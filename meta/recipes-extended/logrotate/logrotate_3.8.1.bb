DESCRIPTION = "Rotates, compresses, removes and mails system log files"
SECTION = "console/utils"
HOMEPAGE = "https://fedorahosted.org/releases/l/o/logrotate"
LICENSE = "GPLv2"
PR = "r1"

DEPENDS="coreutils popt"

LIC_FILES_CHKSUM = "file://COPYING;md5=18810669f13b87348459e611d31ab760"

SRC_URI = "https://fedorahosted.org/releases/l/o/logrotate/logrotate-${PV}.tar.gz \
           file://act-as-mv-when-rotate.patch \
           file://disable-check-different-filesystems.patch \
           file://update-the-manual.patch \
           file://logrotate.service \
           file://logrotate.timer \
           "

SRC_URI[md5sum] = "bd2e20d8dc644291b08f9215397d28a5"
SRC_URI[sha256sum] = "c12471e70ae8bc923bd5c4f25e8fd6483b68c6301f3cd79f7cfe37bc5b370169"

inherit systemd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "logrotate.service logrotate.timer"

PACKAGES += " logrotatetimer "

EXTRA_OEMAKE = "CC='${CC}'"

do_install(){
    oe_runmake install DESTDIR=${D} PREFIX=${D} MANDIR=${mandir}
}

do_install_append(){
    install -d ${D}${sysconfdir} \
    	       ${D}${sysconfdir}/logrotate.d \
    	       ${D}${sysconfdir}/cron.daily \
    	       ${D}${localstatedir}/lib \
	       ${D}${systemd_unitdir}/system

    install -p -m 644 examples/logrotate-default ${D}${sysconfdir}/logrotate.conf
    install -p -m 755 examples/logrotate.cron ${D}${sysconfdir}/cron.daily/logrotate
    touch ${D}${localstatedir}/lib/logrotate.status

    install -m 644 ${WORKDIR}/*.service ${D}/${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/*.timer ${D}/${systemd_unitdir}/system
}
