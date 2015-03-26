SUMMARY = "Dynamic Erlang release management"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;beginline=4;endline=4;md5=b9978f90bd7e8e056279603677171252"

PR = "r1"

SRC_URI = "https://github.com/travelping/enit/archive/${PV}.tar.gz;downloadfilename=enit-${PV}.tar.gz"
SRC_URI[md5sum] = "67a00e33c5d730becef839f392f7da51"
SRC_URI[sha256sum] = "a8039c62bda383d295b090a90000a183b07b4e9e64449f044b089253d48f1ff0"

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
