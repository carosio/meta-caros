require erlang.inc

DEPENDS += "systemd"

SRC_URI = "https://github.com/erlang/otp/archive/OTP-${PV}.tar.gz \
           file://epmd.service \
           file://epmd.socket"
SRC_URI[md5sum] = "e469c7f2c4a632954af4f4b056337d8d"
SRC_URI[sha256sum] = "55998adf6e10d6fb2bcacb5e3fbb52d5250e40e6651cf0592aef92cd7682dbcf"

S = "${WORKDIR}/otp-OTP-${PV}"

PE = "1"
PR = "r1"

SYSTEMD_SERVICE_erlang-epmd += "epmd.socket"

EXTRA_OECONF += "--enable-systemd"

do_configure_prepend() {
    ./otp_build autoconf
}

do_install_append() {
    install -m 0644 ${WORKDIR}/epmd.socket ${D}${systemd_unitdir}/system/
}