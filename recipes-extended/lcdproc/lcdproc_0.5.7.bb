DESCRIPTION = "LCDproc is a client/server suite to drive all kinds of LCD (-like) devices. The client \
shipped with this package can be used to acquire various kinds of system stats."
SUMMARY = "Drivers for character-based LCD displays"
HOMEPAGE = "http://lcdproc.org"
SECTION = "utils"
LICENSE = "GPLv2+"
# DEPENDS = "virtual/libusb0 ncurses"
DEPENDS = "libusb ncurses"

LIC_FILES_CHKSUM = "file://COPYING;md5=18810669f13b87348459e611d31ab760 \
                    file://README;beginline=60;md5=637e042cdd3671ba00e78b58ede45d3b"

PR = "r1"

SRC_URI = "${SOURCEFORGE_MIRROR}/lcdproc/${P}.tar.gz \
	   file://fix_sem_ops.patch \
	   file://0001-Add-a-new-lcm162-subdriver-to-the-hd44780-display-dr.patch \
	   file://lcdproc.service \
	   file://LCDd.service"

SRC_URI[md5sum] = "2f8e064233ef1d03e98c0b426595750d"
SRC_URI[sha256sum] = "843007d377adc856529ed0c7c42c9a7563043f06b1b73add0372bba3a3029804"

inherit autotools pkgconfig systemd

LCD_DRIVERS ?= "all"
LCD_DEFAULT_DRIVER ?= "curses"

EXTRA_OECONF = "--sysconfdir=${sysconfdir}/lcdproc --enable-drivers=${LCD_DRIVERS} \
                --enable-libusb --disable-libpng \
	        ${@bb.utils.contains('DISTRO_FEATURES', 'x11', '', '--disable-libX11', d)}"

SYSTEMD_PACKAGES = "${PN} lcdd"
SYSTEMD_SERVICE_${PN} = "lcdproc.service"
SYSTEMD_SERVICE_lcdd = "LCDd.service"

do_install () {
	# binaries
	install -D -m 0755 server/LCDd ${D}${sbindir}/LCDd
	install -D -m 0755 clients/lcdproc/lcdproc ${D}${bindir}/lcdproc
	install -D -m 0755 clients/lcdvc/lcdvc ${D}${sbindir}/lcdvc

	# configuration files
	install -d ${D}${sysconfdir}/lcdproc
	install -m 0644 ${S}/LCDd.conf ${D}${sysconfdir}/lcdproc/LCDd.conf
	sed -i 's!^DriverPath=.*!DriverPath=${libdir}/lcdproc/!' ${D}${sysconfdir}/lcdproc/LCDd.conf
	sed -i 's!^Driver=.*!Driver=${LCD_DEFAULT_DRIVER}!' ${D}${sysconfdir}/lcdproc/LCDd.conf
	install -m 0644 ${S}/clients/lcdproc/lcdproc.conf ${D}${sysconfdir}/lcdproc/lcdproc.conf
	install -D -m 0644 ${S}/clients/lcdvc/lcdvc.conf ${D}${sysconfdir}/lcdvc.conf

	# driver library files
	install -d ${D}${libdir}/lcdproc
	for i in server/drivers/*.so; do
		install -m 0644 $i ${D}${libdir}/lcdproc/
	done

	install -d ${D}${systemd_unitdir}/system
	install -m 644 ${WORKDIR}/*.service ${D}/${systemd_unitdir}/system
}

PACKAGES =+ "lcdd"
PACKAGES =+ "lcdvc"
PACKAGECONFIG ?= ""

RRECOMMENDS_${PN} = "lcdd"

FILES_lcdd = " \
    ${sysconfdir}/lcdproc/LCDd.conf \
    ${sbindir}/LCDd \
    ${systemd_unitdir}/system/LCDd.service \
"
FILES_lcdvc = "${sysconfdir}/lcdvc.conf ${sbindir}/lcdvc"

CONFFILES_${PN} = "${sysconfdir}/lcdproc/lcdproc.conf"
CONFFILES_lcdd = "${sysconfdir}/lcdproc/LCDd.conf"
CONFFILES_lcdvc = "${sysconfdir}/lcdvc.conf"

# Driver packages

# USB / no USB trickery

RCONFLICTS_lcdd-driver-hd47780nousb = "lcdd-driver-hd44780"
RCONFLICTS_lcdd-driver-hd47780 = "lcdd-driver-hd44780nousb"

python populate_packages_prepend() {
    plugindir = d.expand('${libdir}/lcdproc')
    do_split_packages(d, plugindir, '(.*)\.so$', 'lcdd-driver-%s', 'LCDd driver for %s', prepend=True)
}

PACKAGES_DYNAMIC += "^lcdd-driver-.*"

