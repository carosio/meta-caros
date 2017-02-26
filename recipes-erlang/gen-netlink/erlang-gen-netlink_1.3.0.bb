SUMMARY = "Netlink socket toolkit"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/netlink.erl;beginline=1;endline=19;md5=9c3283402c854efc9254063f52b48bfd"

PR = "r0"

SRCREV = "765184761840aa271d7abb86c9185d66dc88dda0"
SRC_URI = "https://github.com/travelping/gen_netlink/archive/${SRCREV}.tar.gz;downloadfilename=gen_netlink-${PV}.tar.gz"
SRC_URI[md5sum] = "4136344dd01e880175805588c919b76b"
SRC_URI[sha256sum] = "f3bda85fd3c09912b4b9665b992274347c6a81cc055de1bf0363527a006075a1"

#S = "${WORKDIR}/gen_netlink-${PV}"
S = "${WORKDIR}/gen_netlink-${SRCREV}"

TETRAPAK_OPTS += "-o build.version ${PV}-${PR}"

DEPENDS = "erlang-gen-socket erlang-lager erlang-flower"
RDEPENDS_${PN} = "erlang-flower"

inherit tetrapak

python () {
    erlang_def_package("gen-netlink", "gen_netlink-*", "ebin", "src include test cover.sh README.md rebar.config* .travis.yml", d)
}
