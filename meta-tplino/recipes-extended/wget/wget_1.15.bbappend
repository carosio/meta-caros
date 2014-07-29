DEPENDS += "zlib "
RDEPENDS_${PN} += "ca-certificates openssl"

PR := "${PR}.1"

do_install_append() {
        echo "" >> ${D}${sysconfdir}/wgetrc
        echo "# set certificate directory for wget" >> ${D}${sysconfdir}/wgetrc
        echo "ca_directory=/etc/ssl/certs" >> ${D}${sysconfdir}/wgetrc
}

