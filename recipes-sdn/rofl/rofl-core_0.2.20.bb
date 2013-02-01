DESCRIPTION = "Revised OpenFlow Library Core Libraries (ROFL)"
SECTION = "net"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=5d425c8f3157dbf212db2ec53d9e5132"

DEPENDS = "libcli"

PR = "r0"

SRC_URI = "git://gitolite@codebasin.net/rofl-core.git;protocol=ssh;tag=v${PV}"

S = "${WORKDIR}/git"

inherit autotools

EXTRA_OECONF += "--sysconfdir=/etc/rofl --enable-static --disable-shared --enable-debug"
