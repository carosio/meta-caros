DESCRIPTION = "Erlang UUID"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=e6a600fd5e1d9cbde2d983680233ad02"

SRCREV = "76893975bc7d70b217e8f7490d85d1e50105245a"
PR = "r0"

SRC_URI = "git://git@git.tpip.net/uuid.git;protocol=ssh"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("uuid", "uuid*", "ebin priv", "src include test tetrapak", d)
}
