require erlang.inc

DEPENDS += "systemd"

RREPLACES_${PN} = "erlang16"
RCONFLICTS_${PN} = "erlang16"

SRC_URI = "https://github.com/erlang/otp/archive/OTP-${PV}.tar.gz \
           file://epmd.service \
           file://epmd.socket"
SRC_URI[md5sum] = "0d06e7d3802d63992535116e4d37c1a4"
SRC_URI[sha256sum] = "3fe1de7915cd603ebabe103d5d94b8f440fe57ad8e6f62bd6837b6949e08ba1a"

S = "${WORKDIR}/otp-OTP-${PV}"

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
