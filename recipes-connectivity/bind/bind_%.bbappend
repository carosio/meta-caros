FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://conf-db-255.patch"

PACKAGECONFIG ??= ""
PACKAGECONFIG[readline] = "--with-readline=yes,--with-readline=no,readline"
