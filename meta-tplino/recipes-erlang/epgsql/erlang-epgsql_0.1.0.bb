DESCRIPTION = "PostgreSQL Client"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README;md5=65b51e150aca9b972755828804cf531d"

SRCREV="67b10832c57c6aeba271a6ab4fbc020163813266"
PR = "r2"


SRC_URI = "git://git@git.tpip.net/epgsql.git;protocol=ssh"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("epgsql", "epgsql*", "ebin priv", "src include test_data test_ebin test_src", d)
}
