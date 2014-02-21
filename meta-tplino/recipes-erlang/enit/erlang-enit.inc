DESCRIPTION = "Dynamic Erlang release management"
SECTION = "devel"
LICENSE = "MIT"

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
