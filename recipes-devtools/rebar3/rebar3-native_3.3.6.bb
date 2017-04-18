DESCRIPTION = "A sophisticated build-tool for Erlang projects that follows OTP principles."
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ff253ad767462c46be284da12dda33e8"

PR = "r1"

SRC_URI = "https://github.com/erlang/rebar3/archive/${PV}.tar.gz;downloadfilename=rebar3-${PV}.tar.gz"
SRC_URI[md5sum] = "46db297686b7ccf4971a4de9de2c7e85"
SRC_URI[sha256sum] = "2a3a6f709433a11e3fca51cc106b66e0941e7e7067bbc3f8364cbbad0b40660e"

S = "${WORKDIR}/rebar3-${PV}"

inherit native erlangnative

do_compile() {
    ./bootstrap
}

do_install() {
    install -d 0755 ${D}${bindir}
    cp -a ${S}/rebar3 ${D}${bindir}
}