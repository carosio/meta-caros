SUMMARY = "gen_server2 OTP behaviour - provided as an OTP library"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=6cfe87e918c30c45d02d316752aee8ec"

SRCREV="697e7152f8d843cbf5c8422aa8c78ff6d45a12c6"
PR = "r2"

SRC_URI = "git://github.com/carosio/gen_server2.git"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("gen-server2", "gen_server2*", "ebin priv", "src include", d)
}

