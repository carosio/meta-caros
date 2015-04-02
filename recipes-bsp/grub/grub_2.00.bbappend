PR := "${PR}.1"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://42_tplino"

do_install_append () {
    install -m 0755 ${WORKDIR}/42_tplino ${D}${sysconfdir}/grub.d/42_tplino
}

