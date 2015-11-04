SUMMARY = "Host/service/network monitoring plugins for NRPE/Nagios"
DESCRIPTION = "Nagios is a program that will monitor hosts and services on your \
network, and to email or page you when a problem arises or is \
resolved. Nagios runs on a unix server as a background or daemon \
process, intermittently running checks on various services that you \
specify. The actual service checks are performed by separate plugin \
programs which return the status of the checks to Nagios. This package \
contains those plugins."

HOMEPAGE = "https://www.nagios-plugins.org/"

LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://nagios-plugins.spec;beginline=24;endline=24;md5=cced8b49901709fa7b9968bb88dac559"

SECTION = "devel"

PR = "r4"

SRC_URI = "http://nagios-plugins.org/download/nagios-plugins-${PV}.tar.gz"

SRC_URI[md5sum] = "e199ca874df5723bfaca8c43887b1a79"
SRC_URI[sha256sum] = "c7daf95ecbf6909724258e55a319057b78dcca23b2a6cc0a640b90c90d4feae3"

DEPENDS = "nrpe gettext-native"
inherit autotools-brokensep gettext

EXTRA_OECONF+="--with-nagios-group=daemon"
EXTRA_OECONF+="--without-world-permissions"

EXTRA_OECONF+="--without-perl"
EXTRA_OECONF+="--without-mysql"
EXTRA_OECONF+="--with-openssl=${STAGING_LIBDIR}"

do_install () {
	install -d -m 0755 ${D}${libdir}/nagios-plugins/

	install -o 0 -g 1 -m 0550 plugins/check_disk ${D}${libdir}/nagios-plugins/check_disk
	install -o 0 -g 1 -m 0550 plugins/check_http ${D}${libdir}/nagios-plugins/check_http
	install -o 0 -g 1 -m 0550 plugins/check_load ${D}${libdir}/nagios-plugins/check_load
	install -o 0 -g 1 -m 0550 plugins/check_swap ${D}${libdir}/nagios-plugins/check_swap
	install -o 0 -g 1 -m 0550 plugins/check_tcp  ${D}${libdir}/nagios-plugins/check_tcp

	install -o 0 -g 1 -m 4550 plugins-root/check_icmp ${D}${libdir}/nagios-plugins/check_icmp
}
