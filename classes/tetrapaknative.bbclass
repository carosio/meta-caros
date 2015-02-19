PATH_prepend = "${STAGING_BINDIR_NATIVE}/tetrapak-native:"
DEPENDS += "tetrapak-native"
OECMAKE_TETRAPAKNATIVE_DIR = "${STAGING_BINDIR_NATIVE}/tetrapak-native"

inherit erlangnative

erllibs = "${STAGING_DIR_ERLANG_LIBS}:${TETRAPAK_ERL_LIBS}"

do_compile() {
    mkdir -p ebin
    ERL_LIBS=${erllibs} tetrapak build
}

do_install() {
    rm -rfv ./patches
    ERL_LIBS=${erllibs} tetrapak install -prefix ${D}/${base_prefix}
}
