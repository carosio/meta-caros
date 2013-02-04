DESCRIPTION = "syslog/UDP error logger report handler"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/sasl_syslog.erl;beginline=1;endline=19;md5=1f60e6501975c80d633b7d18d3b961f4"

SRCREV="2580e4d2e3ebd46212351d7c6177f904f3775a95"
PV = "0.1+git${SRCPV}"
PR = "r0"

SRC_URI = "git://git@git.tpip.net/sasl_syslog.git;protocol=ssh"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("sasl-syslog", "sasl_syslog-*", "ebin priv", "src include", d)
}