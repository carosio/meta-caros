DESCRIPTION = "Netlink socket toolkit"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/netlink.erl;beginline=1;endline=19;md5=1ab28ac9e46c60331c620bdf33901f4f"

SRCREV = "${AUTOREV}"
PR = "r3"
PV-orig := "${PV}" 
PV = "${PV-orig}-${PR}-${SRCPV}" 

SRC_URI = "git://github.com/travelping/gen_netlink.git;protocol=git;branch=driver_poll_gen_socket"

S = "${WORKDIR}/git"

DEPENDS = "erlang-gen-socket"
RDEPENDS_${PN} = "erlang-gen-socket"

inherit tetrapak

python () {
    erlang_def_package("gen-netlink", "gen_netlink-*", "ebin", "src include test cover.sh README.md", d)
}
