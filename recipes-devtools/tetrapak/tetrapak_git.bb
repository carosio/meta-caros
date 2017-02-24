SUMMARY = "tetrapak is an extensible build system for Erlang/OTP applications."
SCTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/tetrapak.erl;beginline=1;endline=19;md5=300d83493c235b71e1a4d58e25379bc5"

SRCREV = "33702df7e76dfc62cb6ede3581744b3183695d67"
PR = "r2.2"

SRC_URI = "https://github.com/travelping/tetrapak/archive/${SRCREV}.tar.gz;downloadfilename=tetrapak-${SRCREV}.tar.gz"
SRC_URI[md5sum] = "db23c9bb34e00f523bffdf102125a1c6"
SRC_URI[sha256sum] = "5f9db9cb76d1fe4b211b6c584ec2a1c0694590a7c2e74a402eded3ef2937aed9"

S = "${WORKDIR}/tetrapak-${SRCREV}"

PV = "0.4.28+git${SRCREV}"

inherit erlang tetrapak

python () {
    erlang_def_package("tetrapak", "tetrapak-*", "ebin priv bin", "src include debian NEWS.md EMakefile TODO.md README.md doc", d)
}

do_install() {
    ERL_LIBS=${erllibs} ${S}/bin/tetrapak install -prefix ${D}
}
