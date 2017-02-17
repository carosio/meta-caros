LICENSE = "Apache-2"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=ff253ad767462c46be284da12dda33e8"

SRC_URI_prepend = "https://github.com/erlang/otp/archive/OTP-${PV}.tar.gz;name=tarball "
SRC_URI[tarball.md5sum] = "c0989be4567c0d68c4a78e9045e0e9af"
SRC_URI[tarball.sha256sum] = "c6adbc82a45baa49bf9f5b524089da480dd27113c51b3d147aeb196fdb90516b"


S = "${WORKDIR}/otp-OTP-${PV}"

FILES_${PN} += '${erldir}/erts-*/bin/erl_child_setup'
