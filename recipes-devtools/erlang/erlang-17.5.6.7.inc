LICENSE = "ErlPL-1.1"
LIC_FILES_CHKSUM = "file://EPLICENCE;md5=09f9063ea35bc5bd124df2fda1d9d2c7"

SRC_URI_prepend = "https://github.com/erlang/otp/archive/OTP-${PV}.tar.gz;name=tarball "
SRC_URI[tarball.md5sum] = "2d1212c32877add74ae8b47293a9d37b"
SRC_URI[tarball.sha256sum] = "ea18a78d22b34eb902080c0a384956bd859147434f2c419a467cec68fdd920aa"

S = "${WORKDIR}/otp-OTP-${PV}"
