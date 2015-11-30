DESCRIPTION = "IS-IS with autoconfiguration"
SECTION = "net"

# Package is actually GPL-2.0, however as there are no files
# in this package, I don't know how to build a valid
# LIC_FILES_CHKSUM which is required for any license != CLOSED
LICENSE = "CLOSED"

RDEPENDS_${PN} += " erlang-isis quagga sudo"

NODENAME = "autoisis"
NODEUSER = "autoisis"
NODEGROUP = "autoisis"
NODEUSERCOMMENT = "AutoISIS"
PR = "r3"

SRC_URI = "file://autoisis.sudoers"
CONFFILES_${PN} = "${sysconfdir}/sudoers.d/autoisis"

inherit enitrelease

do_install_append() {
    install -d -m 0550 "${D}${sysconfdir}/sudoers.d"
    install -m 0440 ${WORKDIR}/autoisis.sudoers "${D}${sysconfdir}/sudoers.d/autoisis"
}
