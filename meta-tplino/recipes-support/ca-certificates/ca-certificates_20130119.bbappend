FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

PRINC := "${@int(PRINC) + 1}"

SRC_URI_append = " file://hash_certificates "

FILES_${PN}_append = " ${sysconfdir}/ca-certificates/update.d/hash_certificates "

do_install_append() {
        install -d ${D}${sysconfdir}/ca-certificates/update.d
        install -m 755 ${WORKDIR}/hash_certificates ${D}${sysconfdir}/ca-certificates/update.d
}
