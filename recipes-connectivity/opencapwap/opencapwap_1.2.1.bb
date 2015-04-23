DESCRIPTION = "OpenCAPWAP implementation"
HOMEPAGE = "https://opencapwap.atlassian.net/wiki/display/CAPWAP/Home"
SECTION = "network"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://COPYING;md5=bedf4908d7c0c9b86aeb97756a02b556"

SRCREV = "8ea7368335e800603b9d87d1fb08243c63e3cad8"

PR = "r0"

DEPENDS = "openssl"

PACKAGES =+ "opencapwap-wtp opencapwap-ac"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

inherit autotools systemd

SRC_URI = " \
	git://git@git.tpip.net/opencapwap.git;protocol=ssh \
	file://opencapwap-wtp.service \
	file://config.wtp \
	file://opencapwap-ac.service \
	file://settings.ac.txt \
	file://config.ac \
  file://settings.wtp.txt \
  "

FILES_opencapwap-wtp = " \
	${bindir}/WTP \
	${systemd_unitdir}/system/opencapwap-wtp.service \
  ${sysconfdir}/capwap/config.wtp \
  ${sysconfdir}/capwap/settings.wtp.txt \
  "

CONFFILES_opencapwap-wtp = " \
  ${sysconfdir}/capwap/config.wtp \
  ${sysconfdir}/capwap/settings.wtp.txt \
  "

FILES_opencapwap-ac = " \
	${bindir}/AC \
	${systemd_unitdir}/system/opencapwap-ac.service \
  ${sysconfdir}/capwap/config.ac \
  ${sysconfdir}/capwap/settings.ac.txt \
  "

CONFFILES_opencapwap-ac = " \
  ${sysconfdir}/capwap/config.ac \
  ${sysconfdir}/capwap/settings.ac.txt \
"

S = "${WORKDIR}/git"

CFLAGS += "-DLOCALUDP"

SYSTEMD_SERVICE_opencapwap-wtp = "opencapwap-wtp.service"
SYSTEMD_SERVICE_opencapwap-ac = "opencapwap-ac.service"

do_install_append() {
	     install -d ${D}${bindir} \
		   ${D}${sysconfdir}/capwap \
		   ${D}${systemd_unitdir}/system

       install -m 0644 ${WORKDIR}/opencapwap-wtp.service ${D}/${systemd_unitdir}/system
	     install -m 0644 ${WORKDIR}/config.wtp  ${D}${sysconfdir}/capwap
       install -m 0644 ${WORKDIR}/opencapwap-ac.service ${D}/${systemd_unitdir}/system
	     install -m 0644 ${WORKDIR}/settings.ac.txt  ${D}${sysconfdir}/capwap
       install -m 0644 ${WORKDIR}/settings.wtp.txt  ${D}${sysconfdir}/capwap
	     install -m 0644 ${WORKDIR}/config.ac  ${D}${sysconfdir}/capwap
}

EXTRA_OECONF = "--with-openssl=${STAGING_LIBDIR}/.. --sysconfdir=/etc/capwap "
