DESCRIPTION = "mochiweb adapter for cowboy"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=6b11c32a4494c5202ec465f7577e194c"

SRCREV="acd5740a703e778702bcff6ca276f2afe06510c7"
PR = "r2"

SRC_URI = "git://git@git.tpip.net/mochicow.git;protocol=ssh"

S = "${WORKDIR}/git"

inherit tetrapak

DEPENDS = " erlang-mochiweb erlang-cowboy "

python () {
    erlang_def_package("mochicow", "mochicow*", "ebin priv", "src include", d)
}

