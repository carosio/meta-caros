LICENSE = "ErlPL-1.1"
LIC_FILES_CHKSUM = "file://EPLICENCE;md5=09f9063ea35bc5bd124df2fda1d9d2c7"

SRC_URI_prepend = "https://github.com/erlang/otp/archive/OTP-${PV}.tar.gz;name=tarball "
SRC_URI[tarball.md5sum] = "dcabf23dea7919167206557582f7b7c0"
SRC_URI[tarball.sha256sum] = "9c72896f8df53cc4c1c4012acb98ddd3eddc017965abc3614f17b341d913149a"

S = "${WORKDIR}/otp-OTP-${PV}"