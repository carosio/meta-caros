SUMMARY = "NetFlow protocol decoder/encoder"
SECTION = "net"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=36bb70c6a6eafa64be9f3a7c0f7e2544"

PR = "r0"

SRCREV = "ca85c8c6b7b6e1cf0f905cb6427de3edc87389e0"

SRC_URI = "https://github.com/travelping/netflow/archive/${SRCREV}.tar.gz;downloadfilename=netflow-${SRCREV}.tar.gz"

SRC_URI[md5sum] = "ce247d6ef128f4d639f99c37678a2a27"
SRC_URI[sha256sum] = "daf7de53dbdfb6f5e5cb4c9efd848fd973d98e6f40d3c8bf3c1c557a025af646"

S = "${WORKDIR}/netflow-${SRCREV}"

inherit tetrapak

TETRAPAK_OPTS += "-o build.version ${PV}"

python () {
    erlang_def_package("netflow", "netflow*", "ebin priv", "src include test rebar Makefile rebar.config .gitignore README.md", d)
}
