SUMMARY = "Erlang snmp support"
SECTION = "devel"
LICENSE = "CLOSED"

PR="r1"

DEPENDS = "erlang-eradius"
RDEPENDS_${PN} = "erlang-eradius"

SRCREV = "8f810fa97b988412ea0e917ae08a15ae012bfdd0"
SRC_URI = "git://git@git.tpip.net/eradius_snmp.git;protocol=ssh"

inherit tetrapak

S = "${WORKDIR}/git"

python () {
    erlang_def_package("eradius-snmp", "eradius_snmp*", "ebin priv mibs", "sample src include NEWS.md README.md ", d)
}
