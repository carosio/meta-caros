LICENSE = "Apache-2"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=ff253ad767462c46be284da12dda33e8"

SRC_URI_prepend = "https://github.com/erlang/otp/archive/OTP-${PV}.tar.gz;name=tarball "
SRC_URI[tarball.md5sum] = "49d9ef3ccffc028100e35ff80e7d4d06"
SRC_URI[tarball.sha256sum] = "ca909031b08bb4cb4e5419aabb33886b6f7cb8c7d36e940adc4401536801d30e"

S = "${WORKDIR}/otp-OTP-${PV}"
