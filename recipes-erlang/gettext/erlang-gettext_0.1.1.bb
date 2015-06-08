SUMMARY = "gettext handling"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e6e621747d07ff2f060109eec33234a9"

SRCREV="56a286fb9eaf6e3d371a45e56cbbe0c05ea6900b"
PR = "r2"

SRC_URI = "git://github.com/carosio/erlang-gettext.git"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("gettext", "gettext*", "ebin priv", ".arcconfig src include examples", d)
}
