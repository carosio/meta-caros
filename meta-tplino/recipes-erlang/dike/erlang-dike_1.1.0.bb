DESCRIPTION = "Dike - a framework for distributed computing and storage"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/dike_master.erl;beginline=1;endline=8;md5=69962098c047c6d4113c55dd2f6df435"

PR = "r1"

SRC_URI = "git://git@git.tpip.net/dike.git;protocol=ssh"
SRCREV = "c6a899a038733102f7bfe43413f2d671897ea6b9"

DEPENDS += " erlang-regine erlang-emdb erlang-lager "
RDEPENDS_${PN} += " erlang-regine erlang-emdb erlang-lager "
RDEPENDS_${PN}-dev += " erlang-regine erlang-emdb erlang-lager "

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("dike", "dike-*", "ebin priv", "NEWS.md src include papers formalitaeten .arcconfig thesis tsung-radiusclient LICENSE ChangeLog README.md test", d)
}


