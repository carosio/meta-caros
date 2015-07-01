SUMMARY = "Dike - a framework for distributed computing and storage"
SECTION = "devel"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://src/dike_master.erl;beginline=1;endline=8;md5=69962098c047c6d4113c55dd2f6df435"

SRC_URI = "https://github.com/travelping/dike/archive/${PV}.tar.gz;downloadfilename=${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "f8fef2b4ba2135239dbc1d2ca4707608"
SRC_URI[sha256sum] = "0306a7870dd665d82fe7e499945697cfc4ca6403a8c56822f74976990cfb7dc8"

PR = "r1"

DEPENDS = "erlang-regine erlang-emdb erlang-lager"
RDEPENDS_${PN} += "erlang-emdb"
RDEPENDS_${PN}-dev += "erlang-emdb"

S = "${WORKDIR}/dike-${PV}"

inherit tetrapak

TETRAPAK_OPTS += "-o build.version ${PV}"

python () {
    erlang_def_package("dike", "dike-*", "ebin priv", "rebar.config NEWS.md src include papers formalitaeten .arcconfig thesis tsung-radiusclient LICENSE ChangeLog README.md test", d)
}
