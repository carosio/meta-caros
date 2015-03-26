SUMMARY = "MySQL Library"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING.txt;md5=419886945d428c4b26dd4a4f5b2b222b"

SRCREV = "16cae84b5e6e2a08a2fbced8fd4187c188a17868"
PR = "r4"

SRC_URI = "https://github.com/dizzyd/erlang-mysql-driver/archive/${SRCREV}.tar.gz;downloadfilename=erlang-mysql-driver-${SRCREV}.tar.gz \
           file://fix-utf8-encoding.patch \
           file://config.ini"
SRC_URI[md5sum] = "95d89365d36d9c37750c9741d32eeb58"
SRC_URI[sha256sum] = "3acf31721c00bdc5e633cdc0c4b5c9e075308f55498f4f113ef4b5da29f3c341"

S = "${WORKDIR}/erlang-mysql-driver-${SRCREV}"

TETRAPAK_OPTS += "-o build.version 0.3.4~16cae84"

inherit tetrapak

addtask do_prepare_compile after do_patch before do_compile

do_prepare_compile() {
    # add tetrapak
    mkdir -p ${S}/tetrapak
    cp ${WORKDIR}/config.ini ${S}/tetrapak
}

python () {
    erlang_def_package("mysql", "mysql*", "ebin priv", "src include test tetrapak", d)
}
