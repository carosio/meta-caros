require erlang.inc

LICENSE = "Apache-2"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=ff253ad767462c46be284da12dda33e8"

DEPENDS += "systemd"

RREPLACES_${PN} = "erlang16"
RCONFLICTS_${PN} = "erlang16"

SRC_URI = "https://github.com/erlang/otp/archive/OTP-${PV}.tar.gz \
           file://epmd.service \
           file://epmd.socket"
SRC_URI[md5sum] = "549d0aa593a87054a4bb3bedcba5089e"
SRC_URI[sha256sum] = "e014f4248b113698ca35412fde22646f5aab804b5e1f338d21345414d244d467"

S = "${WORKDIR}/otp-OTP-${PV}"

PE = "1"
PR = "${INC_PR}.1"

SYSTEMD_SERVICE_erlang-epmd += "epmd.socket"

EXTRA_OECONF += "--enable-systemd"

do_configure_prepend() {
    ./otp_build autoconf
}

do_install_append() {
    install -m 0644 ${WORKDIR}/epmd.socket ${D}${systemd_unitdir}/system/
}
