require erlang-base.inc

PROVIDES += "virtual/erlang-native"

DEPENDS += "openssl-native"

inherit autotools native

EXTRA_OECONF = "\
	     --enable-smp-support \
	     --without-java \
	     --with-ssl=${STAGING_LIBDIR_NATIVE}/.."

do_configure() {
        oe_runconf
}
