SUMMARY = "Erlang RADIUS server"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://MIT_LICENSE;md5=7a8830d51731fc79efbb8166db8d2859"

S = "${WORKDIR}/git"

SRCREV="f3aebb10ba57843ddc987d7e5ac97544a2419921"

PR = "r1"

SRC_URI = "git://github.com/travelping/eradius.git;protocol=git"
SRC_URI += "file://tetrapak-exclude-rebar.patch"

inherit tetrapak

DEPENDS = "erlang-lager"

python () {
    erlang_def_package("eradius", "eradius-*", "ebin priv", ".travis.yml .pc src include mibs test NEWS.md README_eradius_journal.adoc README.md MIT_LICENSE MAINTAINERS", d)
}
