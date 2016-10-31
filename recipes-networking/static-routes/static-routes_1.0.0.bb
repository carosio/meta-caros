SUMMARY = "A service to add static routes"
SECTION = "netwroking"

LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE;md5=815ca599c9df247a0c7f619bab123dad"

inherit systemd

PR = "r1"

SRC_URI = "file://LICENSE \
           file://static-routes@.service \
           file://static-routes.sh"

FILES_${PN} += "${systemd_unitdir}/system/static-routes@.service \
                /usr/libexec/static-routes.sh \
                /etc/static-routes.d"

do_install() {
  install -d ${D}${systemd_unitdir}/system \
    ${D}/usr/libexec \
    ${D}/etc/static-routes.d

  install -m 0644 ${WORKDIR}/static-routes@.service ${D}${systemd_unitdir}/system
  install -m 0744 ${WORKDIR}/static-routes.sh ${D}/usr/libexec
}
