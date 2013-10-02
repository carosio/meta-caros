DESCRIPTION = "Small, fast, modular HTTP server written in Erlang."
SECTION = "devel"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8a87a00323cbb9706ca2b35e5107b437"

SRCREV="3b4c0588e9bb98307576f7d6e0b52ea434138e62"
PR = "r2"

SRC_URI = "git://github.com/extend/cowboy.git;protocol=git"

S = "${WORKDIR}/git"

inherit tetrapak

DEPENDS = " erlang-ranch "
RDEPENDS_${PN} = " erlang-ranch "

python () {
    erlang_def_package("cowboy", "cowboy*", "ebin priv", ".gitignore CONTRIBUTING.md CHANGELOG.md LICENSE AUTHORS README.md ROADMAP.md rebar.config erlang.mk Makefile manual guide examples src include test", d)
} 
