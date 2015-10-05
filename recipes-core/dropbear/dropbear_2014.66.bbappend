FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "use-only-strong-crypto-config.patch \
	    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', '', 'file://disable-x11fwd.patch', d)}"
