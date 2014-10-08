DESCRIPTION = "tetrapak is an extensible build system for Erlang/OTP applications."
SCTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/tetrapak.erl;beginline=1;endline=19;md5=300d83493c235b71e1a4d58e25379bc5"

PR = "r0"

SRC_URI = "git://git@git.tpip.net/tetrapak.git;protocol=ssh"
SRCREV = "${AUTOREV}"
PV = "git${SRCPV}"
PR = "r0"

DEFAULT_PREFERENCE = "-1"

S = "${WORKDIR}/git"

inherit erlang tetrapak

python () {
    erlang_def_package("tetrapak", "tetrapak-*", "ebin priv bin", "src include debian NEWS.md EMakefile TODO.md README.md doc", d)
}

do_install() {
    ERL_LIBS=${erllibs} ${S}/bin/tetrapak install -prefix ${D}
}
