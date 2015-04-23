SUMMARY = "Erlang RADIUS server"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://MIT_LICENSE;md5=7a8830d51731fc79efbb8166db8d2859"

S = "${WORKDIR}/git"

SRCREV="474bed071514269f1d93c012fa6fdd0b93e22ef8"

PR = "r2"

SRC_URI = "git://github.com/travelping/eradius.git;protocol=git"
SRC_URI += "file://tetrapak-exclude-rebar.patch"

inherit tetrapak

DEPENDS = "erlang-lager"

python () {
    erlang_def_package("eradius", "eradius-*", "ebin priv", ".pc src include mibs test NEWS.md README_eradius_journal.adoc README.md MIT_LICENSE", d)
}
