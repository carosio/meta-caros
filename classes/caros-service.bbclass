#####################################################
# For this class these variables must be set:
#
# APP_PREFIX              (default is "/opt/apps")
# APPNAME                 (default is "${PN}")
# APPUSER                 (default is "carosapp")
# APPGROUP                (default is "carosapp")
# APPVERSION              (default is "${PV}")
# APP_CONTROL             (default is "/usr/caros-apps/libexec/appctl.sh")
# CAROS_APP_SERVICE_${PN} (default is " ")
#####################################################

inherit systemd

RDEPENDS_${PN} += " app-mgmt carosapp "
DEPENDS_${PN} += " carosapp "

APPNAME ??= "${PN}"
APPVERSION ??= "${PV}"
APP_PREFIX ??= "/opt/apps"

APPUSER ??= "carosapp"
APPGROUP ??= "carosapp"

USERNAME_carosapp ?= "${APPUSER}"
GROUPNAME_carosapp ?= "${APPGROUP}"

APP_CONTROL ?= "/usr/caros-apps/libexec/appctl.sh"

PACKAGESPLITFUNCS_prepend = "carossrv_populate_packages "

CAROS_APP_SERVICE_${PN} ?= " "
SYSTEMD_SERVICE_${PN}_append = " ${CAROS_APP_SERVICE_${PN}}"

# this basically undoes systemd.bbclass' service-controlling
# postinst+prerm, by "defusing" systemctl executable
# the implicit daemon-reload from enable+disable stays.
python carossrv_populate_packages() {
    pkg = d.getVar('PN', True)

    sysctl = "systemctl() {\n[ \"$1\" = disable -o \"$1\" = enable ] && /bin/systemctl daemon-reload \n true \n}\n"

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

do_install_append() {
    if [ '${CAROS_APP_SERVICE_${PN}}' != " " ]; then
        SYSTEMD_UNIT_NAME="${CAROS_APP_SERVICE_${PN}}"
        SYSTEMD_UNIT_NAME="${SYSTEMD_UNIT_NAME%.service}"
        install -d ${D}${systemd_unitdir}/system/;
        install -m 0644 ${APP_CONTROL_FILES}/app-template.service ${D}${systemd_unitdir}/system/${CAROS_APP_SERVICE_${PN}};
        sed -i "s|@@DESCRIPTION@@|${DESCRIPTION}|" ${D}${systemd_unitdir}/system/${CAROS_APP_SERVICE_${PN}};
        sed -i "s|@@SUMMARY@@|${SUMMARY}|" ${D}${systemd_unitdir}/system/${CAROS_APP_SERVICE_${PN}};
        sed -i "s|@@APPNAME@@|${APPNAME}|" ${D}${systemd_unitdir}/system/${CAROS_APP_SERVICE_${PN}};
        sed -i "s|@@APPUSER@@|${APPUSER}|" ${D}${systemd_unitdir}/system/${CAROS_APP_SERVICE_${PN}};
        sed -i "s|@@APPGROUP@@|${APPGROUP}|" ${D}${systemd_unitdir}/system/${CAROS_APP_SERVICE_${PN}};
        sed -i "s|@@APPVERSION@@|${APPVERSION}|" ${D}${systemd_unitdir}/system/${CAROS_APP_SERVICE_${PN}};
        sed -i "s|@@APP_PREFIX@@|${APP_PREFIX}|" ${D}${systemd_unitdir}/system/${CAROS_APP_SERVICE_${PN}};
        sed -i "s|@@APP_CONTROL@@|${APP_CONTROL}|" ${D}${systemd_unitdir}/system/${CAROS_APP_SERVICE_${PN}};
        sed -i "s|@@CONFFILE@@|${CONFFILE}|" ${D}${systemd_unitdir}/system/${CAROS_APP_SERVICE_${PN}};
        sed -i "s|@@SYSTEMD_UNIT_NAME@@|${SYSTEMD_UNIT_NAME}|" ${D}${systemd_unitdir}/system/${CAROS_APP_SERVICE_${PN}};
    fi
}

