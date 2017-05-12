SUMMARY = "BGP/OSPF/RIP routing daemon"
DESCRIPTION = "The BIRD project aims to develop a fully functional dynamic IP routing daemon \
primarily targeted on (but not limited to) Linux, FreeBSD and other UNIX-like \
systems and distributed under the GNU General Public License."
HOMEPAGE = "http://bird.network.cz"
SECTION = "network"
LICENSE = "GPL-2.0"
DEPENDS = "readline ncurses"

LIC_FILES_CHKSUM = "file://README;beginline=29;endline=41;md5=1f0a42ec48e7a3fdf869e49b1e939adc"

# the "ip" command from busybox is not sufficient (flush by protocol flushes all routes)
RDEPENDS_${PN} += "iproute2"

PR = "r1"

#
# wget < 1.16.2 is broken for passive ftp in some cases,
# see https://lists.gnu.org/archive/html/bug-wget/2015-01/msg00007.html
#
# SRC_URI = "ftp://bird.network.cz/pub/bird/${PN}-${PV}.tar.gz"

SRC_URI = "ftp://bird.network.cz/pub/bird/bird-${PV}.tar.gz"
SRC_URI += " file://bird.service"
SRC_URI += " file://prepare-environment"
SRC_URI += " file://envvars"

SRC_URI[md5sum] = "330bde6372afa106abfe5a9737e471c1"
SRC_URI[sha256sum] = "79d5c4254e25f5890626830ca42aae03735716bdf152ddd8368473d2b3c17b5d"

inherit autotools caros-service useradd

SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE_${PN} = "bird.service"

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "--system bird"
USERADD_PARAM_${PN} = "--system --home /run/bird -M -g bird --shell /bin/false bird"

CONFFILES_${PN} = "/etc/bird/bird.conf \
		   /etc/bird/envvars"

EXTRA_AUTORECONF += "--exclude=aclocal --exclude=autoheader --exclude=automake"

EXTRA_OECONF = "--enable-pthreads \
	        --sysconfdir=/etc/bird \
		--with-runtimedir=/run/bird \
		--enable-client \
               "

do_install_append() {
    rm -rf ${D}/run

    install -d ${D}${systemd_unitdir}/system \
    	       ${D}/usr/lib/bird
    install -m 0644 ${WORKDIR}/envvars ${D}/etc/bird
    install -m 0644 ${WORKDIR}/bird.service ${D}${systemd_unitdir}/system
    install -m 0755 ${WORKDIR}/prepare-environment ${D}/usr/lib/bird

    chown -R bird:bird ${D}/etc/bird
    chmod -R g-w,o-rwx ${D}/etc/bird
}
