DESCRIPTION = "LCDproc is a client/server suite to drive all kinds of LCD (-like) devices. The client \
shipped with this package can be used to acquire various kinds of system stats."
SUMMARY = "Drivers for character-based LCD displays"
HOMEPAGE = "http://lcdproc.org"
SECTION = "utils"
LICENSE = "GPLv2+"
DEPENDS = "virtual/libusb0 ncurses"

LIC_FILES_CHKSUM = "file://COPYING;md5=18810669f13b87348459e611d31ab760 \
                    file://README;beginline=60;md5=637e042cdd3671ba00e78b58ede45d3b"

SRC_URI = "${SOURCEFORGE_MIRROR}/lcdproc/${P}.tar.gz \
	   file://lcdproc.service \
	   file://LCDd.service \
	  "

inherit autotools systemd

LCD_DRIVERS ?= "all"
LCD_DEFAULT_DRIVER ?= "curses"

EXTRA_OECONF = "--enable-drivers=${LCD_DRIVERS} --enable-libusb --disable-libpng"

SYSTEMD_PACKAGES = "${PN}-systemd lcdd-systemd"
SYSTEMD_SERVICE_${PN}-systemd = "lcdproc.service"
SYSTEMD_SERVICE_lcdd-systemd = "LCDd.service"

do_install () {
	# binaries
	install -D -m 0755 server/LCDd ${D}${sbindir}/LCDd
	install -D -m 0755 clients/lcdproc/lcdproc ${D}${bindir}/lcdproc

	# configuration files
	install -d ${D}${sysconfdir}/lcdproc
	install -m 0644 LCDd.conf ${D}${sysconfdir}/lcdproc/LCDd.conf
	sed -i 's!^DriverPath=.*!DriverPath=${libdir}/lcdproc/!' ${D}${sysconfdir}/lcdproc/LCDd.conf
	sed -i 's!^Driver=.*!Driver=${LCD_DEFAULT_DRIVER}!' ${D}${sysconfdir}/lcdproc/LCDd.conf
	install -m 0644 clients/lcdproc/lcdproc.conf ${D}${sysconfdir}/lcdproc/lcdproc.conf

	# driver library files
	install -d ${D}${libdir}/lcdproc
	for i in server/drivers/*.so; do
		install -m 0644 $i ${D}${libdir}/lcdproc/
	done
}

PACKAGES =+ "lcdd"

RRECOMMENDS_${PN} = "lcdd"

FILES_lcdd = "${sysconfdir}/lcdproc/LCDd.conf \
	      ${sbindir}/LCDd"

CONFFILES_lcdd = "${sysconfdir}/lcdproc/LCDd.conf"
CONFFILES_${PN} = "${sysconfdir}/lcdproc/lcdproc.conf"

# Driver packages

# USB / no USB trickery

RCONFLICTS_lcdd-driver-hd47780nousb = "lcdd-driver-hd44780"
RCONFLICTS_lcdd-driver-hd47780 = "lcdd-driver-hd44780nousb"

python populate_packages_prepend() {
    plugindir = d.expand('${libdir}/lcdproc')
    do_split_packages(d, plugindir, '(.*)\.so$', 'lcdd-driver-%s', 'LCDd driver for %s', prepend=True)
}

PACKAGES_DYNAMIC += "^lcdd-driver-.*"

