DESCRIPTION = "Tetrapak Yang Plugin"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=4de735d102fc94db0f7bd763d7a03434"

INC_PR = "r2"

SRC_URI = "git://git@git.tpip.net/tetrapak_yang.git;protocol=ssh"

S = "${WORKDIR}/git"

DEPENDS = "erlang-yang"

inherit tetrapak

python () {
    erlang_def_package("tetrapak-yang", "tetrapak_yang*", "ebin", "src include README.md tetrapak NEWS.md .gitignore .arcconfig", d)
}
