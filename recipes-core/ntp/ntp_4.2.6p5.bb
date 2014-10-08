require ntp.inc

inherit systemd

PR = "${INC_PR}.0"

SRC_URI[md5sum] = "00df80a84ec9528fcfb09498075525bc"
SRC_URI[sha256sum] = "d6ab8371f9d31e594eb6922823d5ccd03dcc4e9d84b0e23ea25ac1405432f91c"

PRINC := "${@int(PRINC) + 1}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SYSTEMD_PACKAGES = "${PN} ntpdate"
SYSTEMD_SERVICE_${PN}= "ntpd.service"
SYSTEMD_SERVICE_ntpdate= "ntpdate.service"

SRC_URI += " \
        file://ntpdate.service \
        file://ntpd.service \
"
