DESCRIPTION = "ifplugd is a Linux daemon which will automatically configure your ethernet device \
when a cable is plugged in and automatically unconfigure it if the cable is pulled."
HOMEPAGE = "http://0pointer.de/lennart/projects/ifplugd/"
SECTION = "network"
DEPENDS = "libdaemon"
LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://LICENSE;md5=94d55d512a9ba36caa9b7df079bae19f"
PR = "r1"

S = "${WORKDIR}/ifplugd-0.28"

inherit autotools systemd

SRC_URI = " \
    http://0pointer.de/lennart/projects/ifplugd/ifplugd-0.28.tar.gz \
    file://ifplugd.service"

SYSTEMD_SERVICE_${PN} = "${PN}.service"

do_install_append () {
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/ifplugd.service ${D}${systemd_unitdir}/system/.
}

FILES_${PN} = "${systemd_unitdir}/system/ifplugd.service \
    /usr/sbin/ifplugd \
    /usr/sbin/ifplugstatus \
    /etc/ifplugd/ifplugd.action \
    /etc/ifplugd/ifplugd.conf"

EXTRA_OECONF = "--disable-lynx"

CONFFILES_${PN} = "${sysconfdir}/ifplugd/ifplugd.conf"

SRC_URI[md5sum] = "df6f4bab52f46ffd6eb1f5912d4ccee3"
SRC_URI[sha256sum] = "474754ac4ab32d738cbf2a4a3e87ee0a2c71b9048a38bdcd7df1e4f9fd6541f0"
