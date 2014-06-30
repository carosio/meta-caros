require erlang-native.inc

DEFAULT_PREFERENCE = "-1"

SRC_URI = "https://github.com/erlang/otp/archive/OTP-${PV}.tar.gz \
           file://epmd.service"
SRC_URI[md5sum] = "a09daf07965c8abb437a48209aa547e3"
SRC_URI[sha256sum] = "34a0281cfbdd46d98a79b167b73cc0e46cee736ce661685b6c70fa5fd4ab69b3"

S = "${WORKDIR}/otp-OTP-${PV}"

PE = "1"
PR = "r0"

do_configure_prepend() {
    ./otp_build autoconf
}
