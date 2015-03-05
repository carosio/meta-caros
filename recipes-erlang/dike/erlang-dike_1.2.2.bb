DESCRIPTION = "Dike - a framework for distributed computing and storage"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/dike_master.erl;beginline=1;endline=8;md5=69962098c047c6d4113c55dd2f6df435"

SRC_URI = "https://github.com/travelping/dike/archive/${PV}.tar.gz;downloadfilename=${PN}${PV}.tar.gz"
SRC_URI[md5sum] = "df5074d4f9c10b39c8f18c760b3db472"
SRC_URI[sha1sum] = "ddddde8f6ac4f2b517fc5f27c51585f63b090f6e"

PR = "r0"

DEPENDS = "erlang-regine erlang-emdb erlang-lager"
RDEPENDS_${PN} += "erlang-regine erlang-emdb erlang-lager"
RDEPENDS_${PN}-dev += "erlang-regine erlang-emdb erlang-lager"

S = "${WORKDIR}/dike-${PV}"

inherit tetrapak

python () {
    erlang_def_package("dike", "dike-*", "ebin priv", "NEWS.md src include papers formalitaeten .arcconfig thesis tsung-radiusclient LICENSE ChangeLog README.md test", d)
}
