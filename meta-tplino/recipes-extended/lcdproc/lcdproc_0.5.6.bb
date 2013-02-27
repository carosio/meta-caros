require lcdproc5.inc

SRC_URI[md5sum] = "df4f5c2c7285eaf6979b9c7768b4877f"
SRC_URI[sha256sum] = "bd2f43c30ff43b30f43110abe6b4a5bc8e0267cb9f57fa97cc5e5ef9488b984a"

SRC_URI += "file://fix_sem_ops.patch \
	    file://0001-Add-a-new-lcm162-subdriver-to-the-hd44780-display-dr.patch"

PACKAGECONFIG ?= ""

do_install_append () {
	# binaries
	install -D -m 0755 clients/lcdvc/lcdvc ${D}${sbindir}/lcdvc

	# configuration files
	install -D -m 0644 clients/lcdvc/lcdvc.conf ${D}${sysconfdir}/lcdvc.conf
}

PACKAGES =+ "lcdvc"
CONFFILES_lcdvc = "${sysconfdir}/lcdvc.conf"
FILES_lcdvc = "${sysconfdir}/lcdvc.conf ${sbindir}/lcdvc"

