SUMMARY = "journald backend for lager"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9db35a42b923528200d82fcbb74c4dc2"
SRCREV = "156ab4ab8789a5a4b0682d923f9182d2c283c33a"

PR = "r1"

SRC_URI = "git://github.com/travelping/lager_journald_backend.git;protocol=git"

DEPENDS_append = " erlang-lager erlang-ejournald "

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("lager-journald-backend", "lager_journald_backend-*", "ebin", "include src LICENSE README.md NEWS.MD", d)
}
