SUMMARY = "Redbug Erlang Debug Framework"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://include/log.hrl;md5=d3d82191c973b3c2fd41a92d71f0b488"

PR = "r1"

SRCREV = "2b62ce05813b26b5f2d81643e5fae49d50b454ff"
SRC_URI = "git://github.com/liveforeverx/redbug.git;protocol=git"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("redbug", "redbug-*", "ebin priv", "src include", d)
}
