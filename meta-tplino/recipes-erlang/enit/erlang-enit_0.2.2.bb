DESCRIPTION = "Dynamic Erlang release management"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/enit.erl;beginline=1;endline=19;md5=fc016e255103ff7b4faba219415dee94"

SRCREV = "7c6cd11a49cc1dd1ad00324939c22a301860e8af"
PR = "r1"

SRC_URI = "git://git@git.tpip.net/enit.git;protocol=ssh"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("enit", "enit-*", "bin ebin priv", "NEWS.md c_src src include test", d)
}

do_install_append() {
    install -d ${D}${sysconfdir}/enit \
	       ${D}${localstatedir}/lib/enit
}
