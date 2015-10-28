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

EXTRA_OECONF+="--with-nagios-user=daemon --with-nagios-group=daemon"

EXTRA_OECONF+="--without-perl"
EXTRA_OECONF+="--without-mysql"
EXTRA_OECONF+="--with-openssl=${STAGING_LIBDIR}"

#do_configure () {
#	oe_runconf
#}

#do_compile () {
#	oe_runmake
#}

#do_install () {
#}

