DESCRIPTION = "A mocking library for Erlang"
SECTION = "devel"
LICENSE = "Apache-2.0"

LIC_FILES_CHKSUM = "file://LICENSE;md5=ff253ad767462c46be284da12dda33e8"

PR = "r1"

SRC_URI = "git://git@git.tpip.net:meck"
SRCREV = "ef454b3e4dd38b05ad70953997cfbd6edde3549e"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("meck", "meck-*", "ebin", "LICENSE CHANGELOG src Makefile test rebar.config README.md NOTICE doc .travis.yml .scripts", d)
}
