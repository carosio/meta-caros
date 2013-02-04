inherit erlang

DEPENDS += "tetrapak-native erlang"

do_compile() {
    ERL_LIBS=${STAGING_DIR_ERLANG_LIBS} tetrapak build
}

do_install() {
    ERL_LIBS=${STAGING_DIR_ERLANG_LIBS} tetrapak install -prefix ${D}
}
