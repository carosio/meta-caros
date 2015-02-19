inherit erlang

TETRAPAK_OPTS ??= ""

DEPENDS += "tetrapak-native virtual/erlang"

erllibs = "${STAGING_DIR_ERLANG_LIBS}:${TETRAPAK_ERL_LIBS}"

do_compile() {
    mkdir -p ebin
    bbnote "Running tetrapak build ${TETRAPAK_OPTS}"
    ERL_LIBS=${erllibs} tetrapak build ${TETRAPAK_OPTS}
}

do_install() {
    bbnote "Running tetrapak install -prefix ${D} ${TETRAPAK_OPTS}"
    rm -rfv ./patches
    ERL_LIBS=${erllibs} tetrapak install -prefix ${D} ${TETRAPAK_OPTS}
}

python do_qa_tetrapak() {
    import ConfigParser
    import ast

    pn = d.getVar('PN', True)
    srcdir = d.getVar('S', True)
    try:
        config = ConfigParser.RawConfigParser()
        config.read(srcdir + '/tetrapak/config.ini')
    except ConfigParser.ParsingError:
        return

    if not config.has_option('package.deb','build_dependencies'):
        return

    deps = bb.utils.explode_deps(d.getVar('DEPENDS', True) or "")
    for dep in ast.literal_eval(config.get('package.deb','build_dependencies')):
        bb.note("Package %s depends on : %s" % (pn, dep))
        if dep == 'nodejs' and 'nodejs-native' not in deps:
            bb.fatal("""nodejs-native required but not in DEPENDS for package %s. Missing DEPENDS nodejs-native?""" % (pn))
}
do_configure[postfuncs] += "do_qa_tetrapak "
