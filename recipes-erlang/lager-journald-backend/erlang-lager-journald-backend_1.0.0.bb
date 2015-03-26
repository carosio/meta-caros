SUMMARY = "journald backend for lager"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9db35a42b923528200d82fcbb74c4dc2"

SRCREV = "3e21122abca34e1bd3209887150f70ec915c7462"
PR = "r0"

SRC_URI = "git://github.com/travelping/lager_journald_backend.git;protocol=git"

DEPENDS_append = " erlang-lager erlang-ejournald "
RDEPENDS_${PN}_append = " erlang-lager erlang-ejournald "

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("lager-journald-backend", "lager_journald_backend-*", "ebin", "include src LICENSE README.md", d)
}
