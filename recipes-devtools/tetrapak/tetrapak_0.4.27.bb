DESCRIPTION = "tetrapak is an extensible build system for Erlang/OTP applications."
SCTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/tetrapak.erl;beginline=1;endline=19;md5=300d83493c235b71e1a4d58e25379bc5"

SRCREV = "${PV}"
PR = "r1"

SRC_URI = "https://github.com/travelping/tetrapak/archive/${SRCREV}.tar.gz;downloadfilename=tetrapak-${SRCREV}.tar.gz"
SRC_URI[md5sum] = "e347844013ac1c96424f44fb009c6a60"
SRC_URI[sha256sum] = "de9dda3dd78b956c3cf125baf7b8ab9cfa4728925207e8bd4b1b117851b83b57"

S = "${WORKDIR}/tetrapak-${SRCREV}"

inherit erlang tetrapak

python () {
    erlang_def_package("tetrapak", "tetrapak-*", "ebin priv bin", "src include debian NEWS.md EMakefile TODO.md README.md doc", d)
}
