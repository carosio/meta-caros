DESCRIPTION = "A hunky Erlang worker pool factory"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3bfd34238ccc26128aef96796a8bbf97"
SRCREV="4bd8b751766b00e337333a27e7a9403451a43000"

PR = "r2"
PV-orig := "${PV}" 
PV = "${PV-orig}-${PR}-${SRCPV}"

SRC_URI = "git://git@git.tpip.net/poolboy.git;protocol=ssh"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("poolboy", "poolboy*", "ebin priv", "LICENSE .gitignore rebar rebar.config UNLICENSE .dialyzer-ignore-warnings src include test", d)
}
