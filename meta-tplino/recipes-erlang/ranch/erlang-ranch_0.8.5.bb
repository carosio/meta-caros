DESCRIPTION = "Socket acceptor pool for TCP protocols."
SECTION = "devel"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=09d6d59d144d8f52d9fe5c1cc8f47f0f"

SRCREV="41705752ffc7e3ecb96eef8374015ead4ca7f699"
PR = "r2"

SRC_URI = "git://github.com/extend/ranch.git;protocol=git"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("ranch", "ranch*", "ebin priv", ".gitignore LICENSE AUTHORS README.md ROADMAP.md erlang.mk guide Makefile examples src include test", d)
}
