DESCRIPTION = "advanced radius monitoring plugin for NRPE/Nagios"

# Right now this recipe provides only the advanced radius monitoring
# plugin. More nagios checks will follow.

HOMEPAGE = "https://www.nagios-plugins.org/"

LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://check_radius_adv.c;beginline=11;endline=23;md5=e324dca762ae58002281d96a9779cd53"

SECTION = "devel"

PR = "r2"

SRC_URI = "git://github.com/carosio/nagios-checks-caros.git;protocol=git"

SRCREV  = "935ea2a5b3c70fa6ae93d4b7c6c758f9e072f346"

S = "${WORKDIR}/git/check_radius_adv/"

inherit autotools-brokensep gettext

do_install () {
	install -d -m 0755 ${D}${libdir}/nagios-plugins/
	install -o 0 -g 1 -m 0550 check_radius_adv ${D}${libdir}/nagios-plugins/check_radius_adv
}

FILES_${PN} = "${libdir}/nagios-plugins/*"
FILES_${PN}-dbg = "${libdir}/nagios-plugins/.debug/*"

# workaround autoconf issues and cross-compile-preventing CC=gcc
do_patch_extra() {
	sed --in-place -e 's/^AC_INIT(.*$//' configure.in
	sed --in-place -e 's/^CC=.*$//' Makefile
}

addtask patch_extra after do_unpack before do_patch

