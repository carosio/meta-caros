DESCRIPTION = "Erlang SMTP server framework"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2836c20a9a9293da518007b3786dbcfb"
SRCREV = "5b62692539ea51c5fb3a033b66a318e7299747f5"

PR = "r2"

SRC_URI = "git://git@git.tpip.net/gen_smtp.git;protocol=ssh"

DEPENDS_append = " erlang-iconv "
RDEPENDS_${PN}_append = " erlang-iconv "

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("gen-smtp", "gen_smtp-*", "ebin priv", "src include testdata LICENSE Makefile Emakefile .gitignore rebar rebar.config README.markdown", d)
}
