DESCRIPTION = "Erlang Implementation of cron"
SECTION = "devel"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e353635574756e02aec3521297f86ada"

SRCREV="4c771ba32d2d01d0bbb5236521adefbfc4620033"
PR = "r1"

SRC_URI = "git://github.com/erlware/erlcron.git;protocol=git \
	   file://add-tetrapak.patch;apply=yes"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("erlcron", "erlcron-*", "ebin priv", "src include", d)
}
