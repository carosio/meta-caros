require erlang.inc

SRC_URI = " git://github.com/RoadRunnr/otp.git;protocol=git;branch=new_crypto_dtls \
            file://epmd.service \
"
SRCREV = "b163024d82cbae444e21f4ea37d1b25c4e375e0e"

S = "${WORKDIR}/git"

PR = "r2"

do_configure_prepend() {
    ./otp_build autoconf
}
