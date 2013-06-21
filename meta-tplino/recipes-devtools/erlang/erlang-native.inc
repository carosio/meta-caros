require erlang-base.inc

DEPENDS += "openssl"

inherit autotools native

EXTRA_OECONF = '\
	     --enable-smp-support \
	     --without-java \
	     --with-ssl \
'

do_configure() {
        oe_runconf
}
