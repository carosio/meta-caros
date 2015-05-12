EXTRA_OECONF += "--enable-compat-libdns_sd"

PACKAGES =+ "libavahi-compat-libdnssd1"

FILES_libavahi-compat-libdnssd1 = "${libdir}/libdns_sd.so.*"

do_install_append() {
	ln -sf avahi-compat-libdns_sd/dns_sd.h ${D}${includedir}/dns_sd.h
}
