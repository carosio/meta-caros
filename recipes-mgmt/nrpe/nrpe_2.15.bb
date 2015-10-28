SUMMARY = "Host/service/network monitoring agent for Nagios"
DESCRIPTION = "NPRE (Nagios Remote Plugin Executor) is a system daemon that \
will execute various Nagios plugins locally on behalf of a \
remote (monitoring) host that uses the check_nrpe plugin. \
Various plugins that can be executed by the daemon are available \
at: http://sourceforge.net/projects/nagiosplug \
 \
This package provides the server-side NRPE plugin for \
Nagios-related applications."
HOMEPAGE = "http://nrpe.sourceforge.net//"

LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://nrpe.spec;beginline=42;endline=42;md5=cced8b49901709fa7b9968bb88dac559"

SECTION = "devel"

PR = "r1"

SRC_URI = "http://downloads.sourceforge.net/project/nagios/nrpe-2.x/nrpe-${PV}/nrpe-${PV}.tar.gz"

SRC_URI[md5sum] = "3921ddc598312983f604541784b35a50"
SRC_URI[sha256sum] = "66383b7d367de25ba031d37762d83e2b55de010c573009c6f58270b137131072"

DEPENDS = "openssl openssl-native"
inherit autotools-brokensep

EXTRA_OECONF="--with-ssl-lib=${STAGING_LIBDIR} --with-ssl-inc=${STAGING_INCDIR}"
EXTRA_OECONF+="--with-nagios-user=daemon --with-nagios-group=daemon"
EXTRA_OECONF+="--with-nrpe-user=daemon --with-nrpe-group=daemon"
EXTRA_OECONF+="--with-ssl=${STAGING_BINDIR_NATIVE}/openssl"

do_configure () {
	# $sslbin points to target space; fix this by patching the call to host-openssl
	sed --in-place -e 's,\$sslbin dhparam,'${STAGING_BINDIR_NATIVE}'/openssl dhparam,' ./configure
	oe_runconf

	# fail if dh.h generation failed
	test -s include/dh.h
}

do_compile () {
	cd src
	oe_runmake nrpe
}

do_install () {
    install -d ${D}${bindir}
    install -m 0755 ${S}/src/nrpe ${D}${bindir}
}
