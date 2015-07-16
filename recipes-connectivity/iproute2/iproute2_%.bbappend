PR := "${PR}.1"

RPROVIDES_${PN}-ss =+ "ss"
PACKAGES =+ "${PN}-ss"

DEPENDS += "db"

FILES_${PN}-ss = "${base_sbindir}/ss"

EXTRA_OEMAKE += "SUBDIRS='lib tc ip misc' DESTDIR='${STAGING_LIBDIR}'"
