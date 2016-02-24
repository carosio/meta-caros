FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PR := "${PR}.1"

SRC_URI += " file://CVE-2015-8777.patch "
SRC_URI += " file://CVE-2015-8779.patch "
SRC_URI += " file://CVE-2015-9761_1.patch "
SRC_URI += " file://CVE-2015-9761_2.patch "
SRC_URI += " file://CVE-2015-8776.patch "
SRC_URI += " file://CVE-2015-7547-poky-fido.patch "

