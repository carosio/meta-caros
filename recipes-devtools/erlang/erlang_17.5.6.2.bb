require erlang.inc

DEPENDS += "systemd"

RREPLACES_${PN} = "erlang16"
RCONFLICTS_${PN} = "erlang16"

SRC_URI = "https://github.com/erlang/otp/archive/OTP-${PV}.tar.gz \
           file://epmd.service \
           file://epmd.socket"
SRC_URI[md5sum] = "a9f7cad6f90aac7d09ee69bcb951a35c"
SRC_URI[sha256sum] = "bc1e7a705eb5b99ccaa947db1831e96e89ad4420395a84294832af9e6c95ce01"

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
