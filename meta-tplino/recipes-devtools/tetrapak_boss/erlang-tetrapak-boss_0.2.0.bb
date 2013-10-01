DESCRIPTION = "Tetrapak Boss Plugin"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=a2a1073b572c6d211c2062a7d529fec5"

SRCREV="8bdcc54f09ca33eb16727667584892fac11db44b"
PR = "r3"

SRC_URI = "git://git@git.tpip.net/tetrapak_boss.git;protocol=ssh"

S = "${WORKDIR}/git"

DEPENDS = "erlang-boss-db"

inherit tetrapak

python () {
    erlang_def_package("tetrapak-boss", "tetrapak_boss*", "ebin", "src include log tetrapak", d)
}
