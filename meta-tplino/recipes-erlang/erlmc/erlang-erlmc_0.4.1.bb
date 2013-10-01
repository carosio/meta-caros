DESCRIPTION = "Erlang binary protocol memcached client"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.markdown;md5=3f00652a3923114cd47e27de723cf13a"

SRCREV = "984324e36c55e4683cc7fc2303694601d5e98536"
PR = "r1"

SRC_URI = "git://git@git.tpip.net/erlmc;protocol=ssh"

S = "${WORKDIR}/git"

inherit tetrapak

DEPENDS_append = " erlang-gen-server2 "
RDEPENDS_${PN}_append = " erlang-gen-server2 "

python () {
    erlang_def_package("erlmc", "erlmc*", "ebin priv", "src include tetrapak", d)
}
