PR := "${PR}.3"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = " file://dropbearkeygen.sh "

FILES_${PN} += " \ 
    ${ROOT_HOME}/dropbearkeygen.sh \
    .ssh"

do_install_append () {
    install -d ${D}${ROOT_HOME} ${D}/.ssh/
    install -m 0700 ${WORKDIR}/dropbearkeygen.sh ${D}${ROOT_HOME}
}
