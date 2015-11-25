inherit systemd

PACKAGESPLITFUNCS_prepend = "carossrv_populate_packages "

# this basically undoes systemd.bbclass' service-controlling
# postinst+prerm, by "defusing" systemctl executable
# the implicit daemon-reload from enable+disable stays.
python carossrv_populate_packages() {
    pkg = d.getVar('PN', True)

    sysctl = "systemctl() {\n[ \"$1\" = disable -o \"$1\" = enable ] && /bin/systemctl daemon-reload \n}\n"

    postinst = d.getVar('pkg_postinst_%s' % pkg, True)
    if not postinst:
        postinst = '#!/bin/sh\n'
    postinst += "#!/bin/sh\n# no-op\n%s\n"%sysctl
    d.setVar('pkg_postinst_%s'%pkg, postinst)

    prerm = d.getVar('pkg_prerm_%s' % pkg, True)
    if not prerm:
        prerm = '#!/bin/sh\n'
    prerm += "#!/bin/sh\n# no-op\n%s\n"%sysctl
    d.setVar('pkg_prerm_%s'%pkg, prerm)
}
