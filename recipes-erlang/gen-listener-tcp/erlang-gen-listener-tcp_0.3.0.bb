SUMMARY = "A generic tcp listener process adhering to OTP design principles"
SECTION = "devel"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r1"

SRC_URI = "https://github.com/travelping/gen_listener_tcp/archive/${PV}.tar.gz;downloadfilename=gen_listener_tcp-${PV}.tar.gz"
SRC_URI[md5sum] = "9538190550a20e014efa8e465e8aabcf"
SRC_URI[sha256sum] = "e8086d137145757af187df732056537133d9b27cd0ad08c1fc458c247c37aa18"

S = "${WORKDIR}/gen_listener_tcp-${PV}"

TETRAPAK_OPTS += "-o build.version ${PV}"

inherit tetrapak

CLEANBROKEN = "1"

python () {
    erlang_def_package("gen-listener-tcp", "gen_listener_tcp-*", "ebin priv", "src include NEWS.md README.md examples", d)
}
