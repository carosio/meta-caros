require erlang.inc

DEPENDS += "systemd"

RREPLACES_${PN} = "erlang16"
RCONFLICTS_${PN} = "erlang16"

SRC_URI = "http://www.erlang.org/download/otp_src_${PV}.tar.gz \
           file://epmd.service \
           file://epmd.socket"
SRC_URI[md5sum] = "346dd0136bf1cc28cebc140e505206bb"
SRC_URI[sha256sum] = "3c28820c981b30c50df8ac2a4a238e5813454fa3ed2ad530bc7948a282846474"

S = "${WORKDIR}/otp_src_${PV}"

PE = "1"
PR = "${INC_PR}.2"

SYSTEMD_SERVICE_erlang-epmd += "epmd.socket"

EXTRA_OECONF += "--enable-systemd"

do_configure_prepend() {
    ./otp_build autoconf
}

do_install_append() {
    install -m 0644 ${WORKDIR}/epmd.socket ${D}${systemd_unitdir}/system/
}
