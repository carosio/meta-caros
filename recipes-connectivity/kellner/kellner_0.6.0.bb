SUMMARY = "kellner - fast ad hoc serving of packages"
DESCRIPTION = "kellner scans a given directory for software packages and creates an index. It then acts as an ad hoc httpd which serves the packages to opkg or other package managers."
SECTION = "net"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"

PR = "r1"

S = "${WORKDIR}/${PN}"

APPVERSION = "${PV}"
DEPENDS += "go-cross"
# This removes the QA warning about the missing build-dep.
# See stupid.go/stupid.patch for the reason why zeromq is needed in the first place.
DEPENDS += "zeromq"

SRCREV = "25780de637f948b6ba73c0a332c499baa283d103"
SRC_URI = "git://github.com/carosio/kellner;destsuffix=${PN} \
           file://stupid.patch \
           file://kellner.service \
           file://config"

inherit caros-service

SYSTEMD_SERVICE_${PN} = "kellner.service"

FILES_${PN} += "${sysconfdir}/kellner.conf \
                ${sysconfdir}/default/kellner \
                ${localstatedir}/packages"
CONFFILES_${PN} = "${sysconfdir}/kellner.conf"

# Get rid of "QA Issue: No GNU_HASH in the elf binary"
INSANE_SKIP_${PN} = "ldflags"

INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

# FIXME: should to be fixed in go recipe or a class:
export CGO_LDFLAGS="--sysroot=${STAGING_DIR_TARGET}"
export CGO_CFLAGS="--sysroot=${STAGING_DIR_TARGET}"

do_install() {
	install -d ${D}${bindir}
	install -d ${D}${sysconfdir}
	install -d ${D}${sysconfdir}/default
	install -d ${D}${systemd_unitdir}/system
	install -d ${D}${localstatedir}/packages

	install -m 0755 ${S}/kellner ${D}${bindir}
    install -m 0644 ${WORKDIR}/kellner.service ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/config ${D}${sysconfdir}/kellner.conf
    install -m 0644 ${WORKDIR}/config ${D}${sysconfdir}/default/kellner
}
