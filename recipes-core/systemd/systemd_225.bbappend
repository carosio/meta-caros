FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PR := "${PR}.10"

SRC_URI += " \
	file://mgmt.network.in \
	file://timesyncd.conf \
	file://logline-formatting.patch \
"

CONFFILES_${PN} += "${sysconfdir}/systemd/timesyncd.conf"
CONFFILES_${PN} += "${sysconfdir}/systemd/network/mgmt.network"
CONFFILES_${PN} += "${sysconfdir}/machine-id"

MGMT_IF ?= "eth0"
MGMT_IF_vmware ?= "ven0"
MGMT_IF_lanner-fw8758 ?= "ge0p5"
MGMT_IF_sun7i-a20-lamobo-r1 ?= "eth0.5"


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
}
