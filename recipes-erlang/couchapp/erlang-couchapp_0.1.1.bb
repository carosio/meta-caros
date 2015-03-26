SUMMARY = "Couch APP"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRCREV="7ff8b04534141be28bcb4451044f2e021c6255b3"
PR = "r2"

SRC_URI = "git://github.com/travelping/couchapp-erlang.git;protocol=git"

DEPENDS_append = " erlang-couchbeam "

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("couchapp", "couchapp-*", "ebin priv", "Emakefile .gitignore test src include", d)
}
