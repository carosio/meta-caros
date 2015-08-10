require erlang-native.inc

LICENSE = "Apache-2"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=ff253ad767462c46be284da12dda33e8"

SRC_URI = "https://github.com/erlang/otp/archive/OTP-${PV}.tar.gz \
           file://epmd.service"
SRC_URI[md5sum] = "549d0aa593a87054a4bb3bedcba5089e"
SRC_URI[sha256sum] = "e014f4248b113698ca35412fde22646f5aab804b5e1f338d21345414d244d467"

S = "${WORKDIR}/otp-OTP-${PV}"

PE = "1"
PR = "${INC_PR}.1"

do_configure_prepend() {
    ./otp_build autoconf
}
