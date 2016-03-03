SUMMARY = "DHCP Server Framework for Erlang"
SECTION = "devel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=59530bdf33659b29e73d4adb9f9f6552"

PR = "r0.3"

SRC_URI = "git://git@github.com/RoadRunnr/dhcp.git;protocol=ssh"
SRCREV = "${AUTOREV}"

DEPENDS = "erlang-lager"

S = "${WORKDIR}/git"

TETRAPAK_OPTS += "-o build.version ${PV}-${PR}"

inherit tetrapak

python () {
    erlang_def_package("dhcp", "dhcp-*", "ebin priv", "include src c_src AUTHORS COPYING README README.md rebar.config .gitignore", d)
}
