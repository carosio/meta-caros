DESCRIPTION = "IS-IS implementation in Erlang"
SECTION = "network"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRCREV = "b2d9432e0e2340b943b4c84e90859ba6c63be3e5"

SRC_URI = "git://git.netdef.org/scm/osr/isis.git;protocol=https \
           file://fix-xrefs.patch \
           file://remove-hostinfo.patch \
           file://add-tetrapak.patch \
           file://fix-yaws-include.patch \
           file://fix-html-path.patch \
           file://no-netlink.patch"

PR="r2"

INSANE_SKIP_${PN} = "dev-deps"
DEPENDS += "erlang-lager erlang-eenum erlang-procket erlang-yaws"
RDEPENDS_${PN} += "erlang-yaws-dev"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("isis", "isis-*", "ebin html", "src", d)
}
