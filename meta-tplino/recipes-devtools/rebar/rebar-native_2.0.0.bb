DESCRIPTION = "A sophisticated build-tool for Erlang projects that follows OTP principles."
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ff253ad767462c46be284da12dda33e8"
SRCREV="6236ae1ce6f66470db63bd56de7f541ab99206fe"

PR = "r1"
PV-orig := "${PV}" 
PV = "${PV-orig}-${PR}-${SRCPV}" 

SRC_URI = "git://github.com/basho/rebar.git;protocol=git"

S = "${WORKDIR}/git"

inherit native erlangnative

do_install() {
    install -d -m755 ${D}${bindir}
    cp -a ${S}/rebar ${D}${bindir}
}
