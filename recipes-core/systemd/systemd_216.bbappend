FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PR := "${PR}.3"

SRC_URI += "file://0001-tp-remote-syslog.patch \
	    file://timesyncd.conf"

CONFFILES_${PN} += "${sysconfdir}/systemd/timesyncd.conf"

# workarround for bug in systemd.class
#
# when a package has only ONE SYSTEMD_PACKAGES, the class will add all units that
# are mentioned in Conflicts to the package. For systemd itself, this will add
# shutdown.target to system-binfmt, which is bogus

# work arround by having system-binfmt in SYSTEMD_PACKAGES twice

SYSTEMD_PACKAGES += "${PN}-binfmt"

do_install_append() {
	install -m 0644 ${WORKDIR}/timesyncd.conf ${D}${sysconfdir}/systemd/timesyncd.conf

        # Remove virtualization condition from timesyncd service
	sed -i -e '/ConditionVirtualization=/d' ${D}${systemd_unitdir}/system/systemd-timesyncd.service
}
