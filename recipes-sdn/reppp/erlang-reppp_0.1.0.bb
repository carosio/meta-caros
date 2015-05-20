DESCRIPTION = "Revised Erlang PPP Implementation"
SECTION = "devel"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"

PR = "r6"
PV-orig := "${PV}"
PV = "${PV-orig}-${PR}-${SRCPV}"

SRC_URI = "git://github.com/carosio/reppp.git"
SRCREV="c3dc10fb1d7ab26c684a9155cf32dbf26c58cacb"

S = "${WORKDIR}/git"
DEPENDS += "erlang-regine erlang-flower erlang-eradius"

inherit tetrapak

python () {
    erlang_def_package("reppp", "ppp-*", "ebin", "src include README test LICENSE", d)
}
