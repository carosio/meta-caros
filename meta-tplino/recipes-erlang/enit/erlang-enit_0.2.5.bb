DESCRIPTION = "Dynamic Erlang release management"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;beginline=4;endline=4;md5=b9978f90bd7e8e056279603677171252"

PR = "r2"

SRC_URI = "https://github.com/travelping/enit/archive/${PV}.tar.gz;downloadfilename=enit-${PV}.tar.gz"
SRC_URI[md5sum] = "573b33c3d96dd4c0a034f47b24c40633"
SRC_URI[sha256sum] = "d73ccbaa85e9ab5c13851084356347b0d23a1e356830f62d55fcefd7a3aa35ea"

S = "${WORKDIR}/enit-${PV}"

inherit tetrapak

TETRAPAK_OPTS += "-o build.version ${PV}"

python () {
    erlang_def_package("enit", "enit-*", "bin ebin priv", "NEWS.md c_src src include test", d)
}

do_install_append() {
    install -d ${D}${sysconfdir}/enit \
	       ${D}${localstatedir}/lib/enit
}
