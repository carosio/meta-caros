DESCRIPTION = "Simple enumerations for Erlang"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ff253ad767462c46be284da12dda33e8"

SRCREV = "35b6e6f446ccada057a875d478a68f342dbd3156"

SRC_URI = "git://github.com/rpt/eenum.git;protocol=https \
           file://add-tetrapak.patch"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("eenum", "eenum-*", "ebin", "src test LICENSE README.md", d)
}
