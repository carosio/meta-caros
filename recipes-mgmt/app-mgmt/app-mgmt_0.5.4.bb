DESCRIPTION="Manage different versions and instances of an app"

LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"

PR = "r1"

SRC_URI = "git://github.com/carosio/app-mgmt.git"
SRCREV = "ef05dcece85058b0a062cfb68932a2d53e567d0d"

S = "${WORKDIR}/git"

do_install() {
    install -d ${D}/usr/caros-apps/libexec
    install -m 0755 ${S}/libexec/appctl.sh ${D}/usr/caros-apps/libexec/.
}

FILES_${PN} = "/usr/caros-apps/libexec/appctl.sh"
PACKAGES = "${PN}"
