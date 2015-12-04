SUMMARY = "Dike - a framework for distributed computing and storage"
SECTION = "devel"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://src/dike_master.erl;beginline=1;endline=8;md5=69962098c047c6d4113c55dd2f6df435"

SRC_URI = "https://github.com/travelping/dike/archive/${PV}.tar.gz;downloadfilename=${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "4d59c9a67bcd683489c514ab4b68b3d3"
SRC_URI[sha256sum] = "4d73932820cc6c722d57f7163c1c34d744d863d2333b44ecf81161e9a1e7fc6e"

PR = "r1"

DEPENDS = "erlang-regine erlang-emdb erlang-lager"
RDEPENDS_${PN} += "erlang-emdb"
RDEPENDS_${PN}-dev += "erlang-emdb"

S = "${WORKDIR}/dike-${PV}"

inherit tetrapak

TETRAPAK_OPTS += "-o build.version ${PV}"

python () {
    erlang_def_package("dike", "dike-*", "ebin priv", "rebar.config NEWS.md src include papers formalitaeten .arcconfig thesis tsung-radiusclient LICENSE ChangeLog README.md test mix.exs mix.lock config ctest config ctest", d)
}
