FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

RDEPENDS_${PN} += "btrfs-tools dosfstools"

SRC_URI += "file://init-install-main.sh \
            file://init-install-finalize.sh"

INSTALL_SCRIPTS = "init-install-main.sh"

do_configure_prepend() {
    sed -i "s@__TARGET_DEVICE__@${TARGET_DEVICE}@g" ${WORKDIR}/init-install.sh
    for file in ${INSTALL_SCRIPTS}; do
        cat ${WORKDIR}/${file} >> ${WORKDIR}/init-install.sh
    done
    cat ${WORKDIR}/init-install-finalize.sh >> ${WORKDIR}/init-install.sh
}
