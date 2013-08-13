DESCRIPTION = "OpenCAPWAP implementation"
HOMEPAGE = "https://opencapwap.atlassian.net/wiki/display/CAPWAP/Home"
SECTION = "network"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=bedf4908d7c0c9b86aeb97756a02b556"

PR = "r3"

DEPENDS = "openssl"

PACKAGES =+ "opencapwap-wtp opencapwap-ac"

SRCREV = "${AUTOREV}"
PV-orig := "${PV}" 
PV = "${PV-orig}-${PR}-${SRCPV}"

inherit autotools systemd

SRC_URI = " \
	git://git@git.tpip.net/opencapwap.git;protocol=ssh \
	file://opencapwap-wtp.service \
	file://settings.wtp.txt \
	file://config.wtp \
	file://opencapwap-ac.service \
	file://settings.ac.txt \
	file://config.ac"

FILES_opencapwap-wtp = " \
	${bindir}/WTP \
	${systemd_unitdir}/system/opencapwap-wtp.service \
	${ROOT_HOME}/config.wtp \
	${ROOT_HOME}/settings.wtp.txt"

FILES_opencapwap-ac = " \
	${bindir}/AC \
	${systemd_unitdir}/system/opencapwap-ac.service \
	${ROOT_HOME}/config.ac \
	${ROOT_HOME}/settings.ac.txt"

S = "${WORKDIR}/git"

CFLAGS += "-DLOCALUDP"

SYSTEMD_SERVICE_opencapwap-wtp = "opencapwap-wtp.service"
SYSTEMD_SERVICE_opencapwap-ac = "opencapwap-ac.service"

do_install_append() {
	install -d ${D}${bindir} \
		   ${D}${ROOT_HOME} \
		   ${D}${systemd_unitdir}/system

        install -m 0644 ${WORKDIR}/opencapwap-wtp.service ${D}/${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/settings.wtp.txt ${D}${ROOT_HOME}
	install -m 0644 ${WORKDIR}/config.wtp ${D}${ROOT_HOME}
        install -m 0644 ${WORKDIR}/opencapwap-ac.service ${D}/${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/settings.ac.txt ${D}${ROOT_HOME}
	install -m 0644 ${WORKDIR}/config.ac ${D}${ROOT_HOME}
}

##EXTRA_OECONF = "--disable-dtls --enable-debug"
EXTRA_OECONF = "--with-openssl=${STAGING_LIBDIR}/.. --enable-debug"
CFLAGS += "-DLOCALUDP"

require platforms/buffalo-wzr-hp-ag300h.inc 
