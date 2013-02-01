DESCRIPTION = "Revised Erlang PPP Implementation"
SECTION = "devel"
LICENSE = "commercial"
LIC_FILES_CHKSUM = "file://src/ppp_app.erl;beginline=1;endline=1;md5=0f89632c1db6ca13ae19de1d649b7acb"

SRCREV="AUTOINC"
PR = "r0"
PV="0.0+git${SRCPV}"

SRC_URI = "git://git@git.tpip.net/reppp.git;protocol=ssh"

S = "${WORKDIR}/git"
DEPENDS += "erlang-regine erlang-flower erlang-eradius"

inherit tetrapak

python () {
    erlang_def_package("reppp", "ppp-*", "ebin", "src include README test", d)
}