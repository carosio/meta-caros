#
#
#

DEPENDS += "erlang-native"

inherit package

python () {
    packagefuncs = (d.getVar('PACKAGEFUNCS', True) or 'read_shlibdeps').split()
    packagefuncs.insert(packagefuncs.index("read_shlibdeps"), "emit_erlang_deps")
    d.setVar('PACKAGEFUNCS', ' '.join(packagefuncs))
}

erldir = "${libdir}/erlang"
erllibdir = "${erldir}/lib"
erlbindir = "${erldir}/bin"
erlincludedir = "${includedir}"

export STAGING_DIR_ERLANG_LIBS="${STAGING_DIR_TARGET}${erllibdir}"

def erlang_def_package(app, appdir, inc, dev, d):
    erllibdir = d.getVar('erllibdir', True)
    appdirs = appdir.split()

    def appendDirs(dirs, erllibdir, appdirs, adddirs):
        for appdir in appdirs:
            for adddir in adddirs.split():
                dirs.append(erllibdir + "/" + appdir + "/" + adddir)
        return ' '.join(dirs)

    packages = (d.getVar('PACKAGES', True) or "").split()

    if 'erlang-' + app in packages:
        packages.remove('erlang-' + app)

    if 'erlang-' + app + '-dev' in packages:
        packages.remove('erlang-' + app + '-dev')

    if 'erlang-' + app + '-dbg' in packages:
        packages.remove('erlang-' + app + '-dbg')

    incdirs = (d.getVar('FILES_erlang-' + app, True) or "")
    incdirs = appendDirs(incdirs.split(), erllibdir, appdirs, inc)
    packages.insert(0, 'erlang-' + app)
    d.setVar('FILES_erlang-' + app, incdirs)

    devdirs = (d.getVar('FILES_erlang-' + app + '-dev', True) or "")
    devdirs = appendDirs(devdirs.split(), erllibdir, appdirs, dev)
    if devdirs != "":
        packages.insert(0, 'erlang-' + app + '-dev')
    d.setVar('FILES_erlang-' + app + '-dev', devdirs)

    packages.insert(0, 'erlang-' + app + '-dbg')
    dbgdirs = (d.getVar('FILES_erlang-' + app + '-dbg', True) or "")
    dbgdirs = appendDirs(dbgdirs.split(), erllibdir, appdirs, "priv/.debug priv/lib/.debug priv/bin/.debug bin/.debug")
    d.setVar('FILES_erlang-' + app + '-dbg', dbgdirs)

    d.setVar('PACKAGES', ' '.join(packages))

ERLRUN = "${STAGING_BINDIR_NATIVE}/erl -noshell"

python emit_erlang_deps() {

    def pkg_add_erlang_deps(pkg, d):
        def app_to_pkg(app):
            return 'erlang-' + app.lower().replace('_', '-')

        erlrun = d.expand("${ERLRUN}")
        pkgdest = d.getVar('PKGDEST', True)
        path = os.path.join(pkgdest, pkg)

        apps = []
        for root, dirs, files in os.walk(path):
            for file in files:
                if file.endswith('.app'):
                    path = os.path.join(root,file)
                    apps.append(path)

        depends = bb.utils.explode_dep_versions(d.getVar('RDEPENDS_' + pkg, True) or "")
        provides = bb.utils.explode_dep_versions(d.getVar('RPROVIDES_' + pkg, True) or "")

        appnames = []
        for app in apps:
           escript = '{ok, C} = file:consult("'+app+'"), {_, AppName, Props} = lists:keyfind(application, 1, C), Apps = proplists:get_value(applications, Props, []), io:format("~s ~s~n", [AppName, string:join([atom_to_list(X) || X <- Apps], " ")]).'
           deps = os.popen(erlrun + " -eval '" + escript + "' -s erlang halt").readlines()[0].split()
           appname = app_to_pkg(deps.pop(0))
           if appname not in provides:
               provides[appname] = ""

           for dep in deps:
               dep = app_to_pkg(dep)
               if dep not in depends:
                   depends[dep] = ""

        d.setVar('RDEPENDS_%s' % pkg, bb.utils.join_deps(depends, commasep=False))
        d.setVar('RPROVIDES_%s' % pkg, bb.utils.join_deps(provides, commasep=False))

    packages = d.getVar('PACKAGES', True)
    expostfixs = (d.getVar('ERLDEPCHAIN_EXCLUDEPOST', True) or '-dev -dbg -staticdev -doc').split()
    pkgs = packages.split()
    for pkg in packages.split():
        for postfix in expostfixs:
            if pkg.endswith(postfix):
                pkgs.remove(pkg)

    for pkg in pkgs:
        pkg_add_erlang_deps(pkg, d)
}
