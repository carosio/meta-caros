require erlang-native.inc
require erlang-${PV}.inc

SRC_URI += "file://epmd.service"

PE = "1"
PR = "${INC_PR}.1"

do_configure_prepend() {
    ./otp_build autoconf
}
