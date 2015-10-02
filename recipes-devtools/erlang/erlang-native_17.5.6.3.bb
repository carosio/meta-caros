require erlang-native.inc

SRC_URI = "https://github.com/erlang/otp/archive/OTP-${PV}.tar.gz \
           file://epmd.service"
SRC_URI[md5sum] = "dcabf23dea7919167206557582f7b7c0"
SRC_URI[sha256sum] = "9c72896f8df53cc4c1c4012acb98ddd3eddc017965abc3614f17b341d913149a"

S = "${WORKDIR}/otp-OTP-${PV}"

PE = "1"
PR = "${INC_PR}.1"

do_configure_prepend() {
    ./otp_build autoconf
}
