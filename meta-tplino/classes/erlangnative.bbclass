PATH_prepend = "${STAGING_BINDIR_NATIVE}/erlang-native:"
DEPENDS += "erlang-native"
OECMAKE_ERLANGNATIVE_DIR = "${STAGING_BINDIR_NATIVE}/erlang-native"

erldir = "${libdir}/erlang"
erllibdir = "${erldir}/lib"
erlbindir = "${erldir}/bin"
erlincludedir = "${includedir}"

export STAGING_DIR_ERLANG_LIBS="${STAGING_DIR_TARGET}${erllibdir}"
