SUMMARY = "Extra machine specific configuration files"
DESCRIPTION = "Extra machine specific configuration files for udev, specifically blacklist information."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit allarch

PR = "r9"

SRC_URI = " \
       file://automount.rules \
       file://mount.sh \
       file://mount.blacklist \
       file://autonet.rules \
       file://network.sh \
       file://localextra.rules \
       file://hotplug.functions \
       file://networking.rules \
       file://net.agent \
       file://ifup@.service \
"

do_install() {
    install -d ${D}${sysconfdir}/udev/rules.d

    install -m 0644 ${WORKDIR}/automount.rules     ${D}${sysconfdir}/udev/rules.d/automount.rules

    if ${@base_contains("DISTRO_FEATURES", "systemd", "true", "false" ,d)}; then
	install -d ${D}/lib/udev
	install -m 0644 ${WORKDIR}/hotplug.functions  ${D}/lib/udev/hotplug.functions

	install -m 0755 ${WORKDIR}/net.agent          ${D}/lib/udev/net.agent
	install -m 0644 ${WORKDIR}/networking.rules   ${D}${sysconfdir}/udev/rules.d/networking.rules

	install -d ${D}${sysconfdir}/systemd/system
	install -m 0644 ${WORKDIR}/ifup@.service   ${D}${sysconfdir}/systemd/system
    else
	install -m 0644 ${WORKDIR}/autonet.rules   ${D}${sysconfdir}/udev/rules.d/autonet.rules
    fi
    install -m 0644 ${WORKDIR}/localextra.rules    ${D}${sysconfdir}/udev/rules.d/localextra.rules

    install -m 0644 ${WORKDIR}/mount.blacklist     ${D}${sysconfdir}/udev/

    install -d ${D}${sysconfdir}/udev/scripts/

    install -m 0755 ${WORKDIR}/mount.sh ${D}${sysconfdir}/udev/scripts/mount.sh
    install -m 0755 ${WORKDIR}/network.sh ${D}${sysconfdir}/udev/scripts
}

FILES_${PN} = "${sysconfdir}/udev /lib/udev ${sysconfdir}/systemd/system"

RDEPENDS_${PN} = "udev"
CONFFILES_${PN} = "${sysconfdir}/udev/mount.blacklist"

# to replace udev-extra-rules from meta-oe
RPROVIDES_${PN} = "udev-extra-rules"
RREPLACES_${PN} = "udev-extra-rules"
RCONFLICTS_${PN} = "udev-extra-rules"
