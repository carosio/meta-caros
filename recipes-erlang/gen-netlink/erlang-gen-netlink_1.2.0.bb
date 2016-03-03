SUMMARY = "Netlink socket toolkit"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/netlink.erl;beginline=1;endline=19;md5=9c3283402c854efc9254063f52b48bfd"

PR = "r0"

SRC_URI = "https://github.com/travelping/gen_netlink/archive/master.tar.gz;downloadfilename=gen_netlink-${PV}.tar.gz"
SRC_URI[md5sum] = "c7c3808891353ef2e89a0bcfdc5edcad"
SRC_URI[sha256sum] = "4c4ce712b93b06c7b51a898ef647a6d6bcc5d70ee1b9c7c81dc0780e93478350"

#S = "${WORKDIR}/gen_netlink-${PV}"
S = "${WORKDIR}/gen_netlink-master"

TETRAPAK_OPTS += "-o build.version ${PV}-${PR}"

DEPENDS = "erlang-gen-socket erlang-lager erlang-flower"
RDEPENDS_${PN} = "erlang-flower"

inherit tetrapak

python () {
    erlang_def_package("gen-netlink", "gen_netlink-*", "ebin", "src include test cover.sh README.md rebar.config* .travis.yml", d)
}
