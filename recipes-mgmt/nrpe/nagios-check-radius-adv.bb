DESCRIPTION = "advanced radius monitoring plugin for NRPE/Nagios"

HOMEPAGE = "https://www.nagios-plugins.org/"

LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://check_radius_adv.c;beginline=11;endline=23;md5=e324dca762ae58002281d96a9779cd53"

SECTION = "devel"

PR = "r1"

SRC_URI = "https://exchange.nagios.org/components/com_mtree/attachment.php?link_id=295&cf_id=29;downloadfilename=${PN}.tar.gz.gz"

SRC_URI[md5sum] = "a7e5fa6c5f3aa90c7b7915420b29658d"
SRC_URI[sha256sum] = "5bd5a1f979e2a33b545c514d6bbb7597442668aaa284fc198f01c5047b642f20"

DEPENDS = "nagios-plugins gettext-native"
inherit autotools-brokensep gettext

do_install () {
	install -d -m 0755 ${D}${libdir}/nagios-plugins/
	install -o 0 -g 1 -m 0550 check_radius_adv ${D}${libdir}/nagios-plugins/check_radius_adv
}

FILES_${PN} = "${libdir}/nagios-plugins/*"
FILES_${PN}-dbg = "${libdir}/nagios-plugins/.debug/*"

# manually unpack (download-location provides a .tar.gz.gz)
# also workaround autoconf issues and cross-compile-preventing CC=gcc
do_unpack_extra() {
	cd ${S}
	tar -xvzf ../${PN}.tar.gz
	sed --in-place -e 's/^AC_INIT(.*$//' configure.in
	sed --in-place -e 's/^CC=.*$//' Makefile
}

addtask unpack_extra after do_unpack before do_patch

