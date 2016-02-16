FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PR := "${PR}.1"

SRC_URI += " file://CVE-2015-7547-poky-jethro.patch "

