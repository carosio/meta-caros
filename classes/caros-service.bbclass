inherit systemd

PACKAGESPLITFUNCS_prepend = "carossrv_populate_packages "

# this basically undoes systemd.bbclass' service-controlling
# postinst+prerm, by "defusing" systemctl executable
python carossrv_populate_packages() {
    pkg = d.getVar('PN', True)

    postinst = d.getVar('pkg_postinst_%s' % pkg, True)
    if not postinst:
        postinst = '#!/bin/sh\n'
    postinst += "#!/bin/sh\n# no-op\nsystemctl() {\n:\n}\n"
    d.setVar('pkg_postinst_%s'%pkg, postinst)

    prerm = d.getVar('pkg_prerm_%s' % pkg, True)
    if not prerm:
        prerm = '#!/bin/sh\n'
    prerm += "#!/bin/sh\n# no-op\nsystemctl() {\n:\n}\n"
    d.setVar('pkg_prerm_%s'%pkg, prerm)
}
