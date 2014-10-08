DESCRIPTION = "A sophisticated build-tool for Erlang projects that follows OTP principles."
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ff253ad767462c46be284da12dda33e8"

PR = "r1"

SRC_URI = "https://github.com/rebar/rebar/archive/${PV}.tar.gz;downloadfilename=rebar-${PV}.tar.gz"
SRC_URI[md5sum] = "4673d5c251def757b35b3af45ee50883"
SRC_URI[sha256sum] = "d585fa4c7f59c66017d2552764bb423abeed3a3c99503be91b41fb931245ccbe"

S = "${WORKDIR}/rebar-${PV}"

inherit native erlangnative

do_install() {
    install -d -m755 ${D}${bindir}
    cp -a ${S}/rebar ${D}${bindir}
}
