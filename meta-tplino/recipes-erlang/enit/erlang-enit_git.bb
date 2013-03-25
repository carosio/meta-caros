DESCRIPTION = "Dynamic Erlang release management"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/enit.erl;beginline=1;endline=19;md5=fc016e255103ff7b4faba219415dee94"

SRCREV="7ff37bd4dd332249a065dd9c63eb1ed3f7305359"
PV = "0.1+git${SRCPV}"
PR = "r0"

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
