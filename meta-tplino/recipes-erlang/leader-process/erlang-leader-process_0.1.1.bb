DESCRIPTION = "Leader process function"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58"

SRCREV="85c085ae2600d35c7ca13cfd57d074c5a4fb8a48"
PR = "r1"

SRC_URI = "git://git@git.tpip.net/leader_process.git;protocol=ssh"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("leader-process", "leader_process-*", "ebin priv", "src include test", d)
}
