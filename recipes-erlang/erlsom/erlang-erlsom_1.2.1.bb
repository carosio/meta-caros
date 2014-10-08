DESCRIPTION = "erlsom XSD parser"
SECTION = "devel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRCREV="def76b9c82d79c0f2b496c9fcdeb3c9b7b9bd41c"
PR = "r0"

SRC_URI = "git://github.com/willemdj/erlsom.git;protocol=git \
           file://add-tetrapak.patch;apply=yes"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("erlsom", "erlsom-*", "ebin priv", "src include examples", d)
}
