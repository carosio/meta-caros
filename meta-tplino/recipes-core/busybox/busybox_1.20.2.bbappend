FILESEXTRAPATHS_prepend := "${THISDIR}/${P}:"

PRINC := "${@int(PRINC) + 3}"

SRC_URI += "file://runit"

PACKAGES =+ "${PN}-runit"
FILES_${PN}-runit = "${sysconfdir}/init.d/runit /var/service"

INITSCRIPT_PACKAGES =+ "${PN}-runit"

INITSCRIPT_NAME_${PN}-runit = "runit"
INITSCRIPT_PARAMS_${PN}-runit = "defaults 90"

do_install_append () {
    install -d ${D}/var/service

    install -m 0755 ${WORKDIR}/runit ${D}${sysconfdir}/init.d/
}