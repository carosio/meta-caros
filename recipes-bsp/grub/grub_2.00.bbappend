PR := "${PR}.1"

export CAROS_GRUB_MENU_CONFIG_FILE = "42_caros"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
SRC_URI += "file://${CAROS_GRUB_MENU_CONFIG_FILE}"

do_install_append () {
    sed -i "s/__DISTRO_NAME__/${DISTRO_NAME}/g" ${WORKDIR}/${CAROS_GRUB_MENU_CONFIG_FILE}
    install -m 0755 ${WORKDIR}/${CAROS_GRUB_MENU_CONFIG_FILE} ${D}${sysconfdir}/grub.d/${CAROS_GRUB_MENU_CONFIG_FILE}
}

