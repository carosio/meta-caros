PR := "${PR}.1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "${@bb.utils.contains('DISTRO_FEATURES', 'x11', '', 'file://disable-x11fwd.patch', d)}"
SRC_URI += "file://use-only-strong-crypto-config.patch"
