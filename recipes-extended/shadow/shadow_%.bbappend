PR := "${PR}.1"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://shadow-lastlog.conf"

FILES_${PN} += "/usr/lib/tmpfiles.d/shadow-lastlog.conf"

do_install_append() {
        install -m 0755 -d ${D}/usr/lib/tmpfiles.d
        install -m 0644 ${WORKDIR}/shadow-lastlog.conf ${D}/usr/lib/tmpfiles.d/shadow-lastlog.conf
}
