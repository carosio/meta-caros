DESCRIPTION = "Netlink socket toolkit"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/netlink.erl;beginline=1;endline=19;md5=9c3283402c854efc9254063f52b48bfd"

SRCREV = "9453e0e3e886869c498ed77aa8e48949b7a9d759"
PR = "r1"

SRC_URI = "git://github.com/travelping/gen_netlink.git;protocol=git;branch=driver_poll_gen_socket"

S = "${WORKDIR}/git"

DEPENDS = "erlang-gen-socket"
RDEPENDS_${PN} = "erlang-gen-socket"

inherit tetrapak

python () {
    erlang_def_package("gen-netlink", "gen_netlink-*", "ebin", "src include test cover.sh README.md", d)
}
