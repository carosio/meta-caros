DESCRIPTION = "IS-IS implementation in Erlang"
SECTION = "network"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=81bcece21748c91ba9992349a91ec11d"

SRCREV = "cfcc404a4a13078104bac431d2f06d993dbe7df9"

SRC_URI = "git://git.netdef.org/scm/osr/isis.git;protocol=https;branch=stable \
           file://add-tetrapak.patch \
           file://fix-yaws-include.patch \
           file://remove-fix-old-debug-code.patch \
           file://fix-html-path.patch"

PR="r2"

INSANE_SKIP_${PN} = "dev-deps"
DEPENDS += "erlang-lager erlang-eenum erlang-procket erlang-yaws"
RDEPENDS_${PN} += "erlang-lager erlang-eenum erlang-procket erlang-yaws erlang-yaws-dev"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("isis", "isis-*", "ebin html", "src", d)
}
