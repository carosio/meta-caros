DESCRIPTION="Manage different versions and instances of an app"

LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"

PR = "r1"

SRC_URI = "git://github.com/carosio/app-mgmt.git"
SRCREV = "0b2d23d359edfd8291106124099ba9e753d0a70a"

S = "${WORKDIR}/git"

do_install() {
    install -d ${D}/usr/caros-apps/libexec
    install -m 0755 ${S}/libexec/appctl.sh ${D}/usr/caros-apps/libexec/.
}

FILES_${PN} = "/usr/caros-apps/libexec/appctl.sh"
PACKAGES = "${PN}"
