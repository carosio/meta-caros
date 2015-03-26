SUMMARY = "Dynamic Erlang release management"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;beginline=4;endline=4;md5=b9978f90bd7e8e056279603677171252"

PR = "r0"

SRC_URI = "git://git@git.tpip.net/enit.git;protocol=ssh"
SRCREV="${AUTOREV}"
PV = "git${SRCPV}"
PR = "r1"

DEFAULT_PREFERENCE = "-1"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("enit", "enit-*", "bin ebin priv", "NEWS.md c_src src include test", d)
}

do_install_append() {
    install -d ${D}${sysconfdir}/enit \
	       ${D}${localstatedir}/lib/enit
}
