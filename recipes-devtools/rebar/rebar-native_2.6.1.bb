DESCRIPTION = "A sophisticated build-tool for Erlang projects that follows OTP principles."
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ff253ad767462c46be284da12dda33e8"

PR = "r1"

SRC_URI = "https://github.com/rebar/rebar/archive/${PV}.tar.gz;downloadfilename=rebar-${PV}.tar.gz"
SRC_URI[md5sum] = "1ae17dd671c0434012c7a688d4cf60e4"
SRC_URI[sha256sum] = "aed933d4e60c4f11e0771ccdb4434cccdb9a71cf8b1363d17aaf863988b3ff60"

S = "${WORKDIR}/rebar-${PV}"

inherit native erlangnative

do_install() {
    install -d -m755 ${D}${bindir}
    cp -a ${S}/rebar ${D}${bindir}
}
