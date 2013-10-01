DESCRIPTION = "gettext handling"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e6e621747d07ff2f060109eec33234a9"

SRCREV="e3cc619a40165088c1ca274eb7cc8e304475f7bf"
PR = "r1"

SRC_URI = "git://git@git.tpip.net/gettext.git;protocol=ssh"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("gettext", "gettext*", "ebin priv", ".arcconfig src include examples", d)
}
