require erlang.inc

S = "${WORKDIR}/otp_src_R15B03"

PR = "r0"

SRC_URI += " file://epmd.service "

SRC_URI[md5sum] = "eccd1e6dda6132993555e088005019f2"
SRC_URI[sha256sum] = "4bccac86dd76aec050252e44276a0283a0df9218e6470cf042a9b9f9dfc9476c"

