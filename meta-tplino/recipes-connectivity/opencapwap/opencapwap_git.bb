DESCRIPTION = "OpenCAPWAP implementation"
HOMEPAGE = "https://opencapwap.atlassian.net/wiki/display/CAPWAP/Home"
SECTION = "network"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=bedf4908d7c0c9b86aeb97756a02b556"

PR = "r0"

DEPENDS = "openssl"

PACKAGES =+ "opencapwap-wtp opencapwap-ac"

SRCREV = "AUTOINC"

inherit autotools systemd

SRC_URI = " \
	git://github.com/travelping/openCAPWAP.git \
	file://adjust_configs.patch \
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
