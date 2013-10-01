DESCRIPTION = "jaderl compiles the Jade template language to Erlang bytecode"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/jaderl.erl;md5=7a9e7abea03db1447e8f7f69595a5f56"

SRCREV="536a66e5c9c5979d08e9c85888470883bd464a31"
PR = "r1"

SRC_URI = "git://git@git.tpip.net/jaderl.git;protocol=ssh"

S = "${WORKDIR}/git"

inherit tetrapak

DEPENDS_append = " erlang-dynamic-compile  "
RDEPENDS_${PN}_append = " erlang-dynamic-compile  "

python () {
    erlang_def_package("jaderl", "jaderl*", "ebin priv", "src include", d)
}
