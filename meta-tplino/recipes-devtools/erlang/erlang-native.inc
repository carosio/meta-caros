require erlang-base.inc

PROVIDES += "virtual/erlang-native"

DEPENDS += "openssl"

inherit autotools native

EXTRA_OECONF = "\
	     --enable-smp-support \
	     --without-java \
	     --with-ssl \
	     --with-crypto \
"

do_configure() {
        oe_runconf
}
