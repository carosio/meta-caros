DESCRIPTION = "Dynamic Erlang release management"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/enit.erl;beginline=1;endline=19;md5=fc016e255103ff7b4faba219415dee94"

SRCREV = "${AUTOREV}"
PR = "r1"
PV-orig := "${PV}"
PV = "${PV-orig}-${PR}-${SRCPV}"

SRC_URI = "git://git@git.tpip.net/enit.git;protocol=ssh"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("enit", "enit-*", "bin ebin priv", "c_src src include", d)
}

do_install_append() {
    install -d ${D}${sysconfdir}/enit \
	       ${D}${localstatedir}/lib/enit
}
