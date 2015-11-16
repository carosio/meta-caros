SUMMARY = "CarOS feed configuration"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PR = "r7"
INHIBIT_DEFAULT_DEPS = "1"

#FEEDNAMEPREFIX ?= "INVALID"
#FEEDURIPREFIX ?= "INVALID"
#FEEDDOMAIN ?= "INVALID"

do_compile() {
	mkdir -p ${S}/${sysconfdir}/opkg/

	ipkgarchs="${ALL_MULTILIB_PACKAGE_ARCHS}"
	basefeedconf=${S}/${sysconfdir}/opkg/base-feeds.conf

	rm -f $basefeedconf
	touch $basefeedconf

	for arch in $ipkgarchs; do
	        echo "src/gz ${FEEDNAMEPREFIX}-$arch ${FEEDDOMAIN}/${FEEDURIPREFIX}/$arch" >> $basefeedconf
	done
}

do_install () {
	install -d ${D}${sysconfdir}/opkg
	install -m 0644  ${S}/${sysconfdir}/opkg/* ${D}${sysconfdir}/opkg/
}

FILES_${PN} = "${sysconfdir}/opkg/ "

CONFFILES_${PN} += "${sysconfdir}/opkg/base-feeds.conf"

