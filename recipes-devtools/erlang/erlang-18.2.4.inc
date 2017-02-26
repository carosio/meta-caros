LICENSE = "Apache-2"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=ff253ad767462c46be284da12dda33e8"

SRC_URI_prepend = "https://github.com/erlang/otp/archive/OTP-${PV}.tar.gz;name=tarball "
SRC_URI[tarball.md5sum] = "0ea13f170092d73f014c27296825bfc9"
SRC_URI[tarball.sha256sum] = "1c9ebe225a9223d428b653b038c75f15c19a239e1100db03eb8d105f1927a54d"

S = "${WORKDIR}/otp-OTP-${PV}"
