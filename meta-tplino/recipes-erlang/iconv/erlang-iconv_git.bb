DESCRIPTION = "iconv"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5bc2fce46aa4a44a5299323a986c3c79"
SRCREV="94fc86a6a64726b8680fb957771f1dd9cea33ce6"

PR = "r0"

SRC_URI = "git://git@git.tpip.net/erlang-iconv.git;protocol=ssh"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("erlang-iconv", "erlang-iconv-*", "ebin priv", "src include README.markdown", d)
}
