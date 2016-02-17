SUMMARY = "A generic tcp listener process adhering to OTP design principles"
SECTION = "devel"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r1"

SRC_URI = "https://github.com/travelping/gen_listener_tcp/archive/${PV}.tar.gz;downloadfilename=gen_listener_tcp-${PV}.tar.gz"
SRC_URI[md5sum] = "e6509990e7946ffe31526a389f0c6b11"
SRC_URI[sha256sum] = "416b126de56f96a590905782e2898a3caf0db95fedce21d8b121683d5db1bf6f"

S = "${WORKDIR}/gen_listener_tcp-${PV}"

inherit rebar
