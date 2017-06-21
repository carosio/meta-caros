FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PR := "${PR}.7"

SRC_URI += " \
	file://0001-tp-remote-syslog.patch \
	file://mgmt.network.in \
	file://journal-remote-fix.patch \
	file://timesyncd.conf \
	file://multiline-fix.patch \
	file://systemd-networkd.after-override.udevd \
"

CONFFILES_${PN} += "${sysconfdir}/systemd/timesyncd.conf"
CONFFILES_${PN} += "${sysconfdir}/systemd/network/mgmt.network"
CONFFILES_${PN} += "${sysconfdir}/machine-id"

MGMT_IF ?= "eth0"
MGMT_IF_vmware ?= "ven0"
MGMT_IF_lanner-fw8758 ?= "ge0p5"


# workarround for bug in systemd.class
#
# when a package has only ONE SYSTEMD_PACKAGES, the class will add all units that
# are mentioned in Conflicts to the package. For systemd itself, this will add
# shutdown.target to system-binfmt, which is bogus

# work arround by having system-binfmt in SYSTEMD_PACKAGES twice

SYSTEMD_PACKAGES += "${PN}-binfmt"

PACKAGECONFIG += "microhttpd"

do_install_append() {
	install -m 0644 ${WORKDIR}/timesyncd.conf ${D}${sysconfdir}/systemd/timesyncd.conf

	# Remove virtualization condition from timesyncd service
	sed -i -e '/ConditionVirtualization=/d' ${D}${systemd_unitdir}/system/systemd-timesyncd.service

	# install default network configuration
	install -d -m 0755 "${D}${sysconfdir}/systemd/network/"
	install -m 0644 ${WORKDIR}/mgmt.network.in ${D}${sysconfdir}/systemd/network/mgmt.network
	sed -i -e "s/@@MGMT_IF@@/${MGMT_IF}/" ${D}${sysconfdir}/systemd/network/mgmt.network

	# install systemd-networkd.service override to start after systemd-udevd
	install -d -m 0755 ${D}${sysconfdir}/systemd/system/systemd-networkd.service.d
	install -m 0644 ${WORKDIR}/systemd-networkd.after-override.udevd ${D}${sysconfdir}/systemd/system/systemd-networkd.service.d/override.conf
}
