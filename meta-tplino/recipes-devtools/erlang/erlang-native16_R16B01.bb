require erlang-native.inc

SRC_URI = "git://github.com/RoadRunnr/otp.git;protocol=git;branch=new_crypto_dtls"
SRCREV = "9e02611a5b01e09eabbe6c7b6b0a911b42c935a8"

S = "${WORKDIR}/git"

PR = "r0"

RCONFLICTS_${PN} = "erlang-native"

do_configure_prepend() {
    ./otp_build autoconf
}
