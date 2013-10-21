require erlang-native.inc

SRC_URI = "git://github.com/RoadRunnr/otp.git;protocol=git;branch=new_crypto_dtls"
SRCREV = "7e29e048fdd715b441bc3d6649b196952321ad52"

S = "${WORKDIR}/git"

PR = "r2"

do_configure_prepend() {
    ./otp_build autoconf
}
