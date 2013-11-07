inherit useradd systemd

RDEPENDS_${PN}_append = " logrotate erlang-enit "

USERADD_PACKAGES = "${PN}"

# Add user and group the node will be running as
GROUPADD_PARAM_${PN} = "${NODEUSER}"
USERADD_PARAM_${PN} = "--create-home \
                       --system \
                       --shell /bin/false \
                       --gid ${NODEUSER} \
                       --comment ${NODEUSERCOMMENT} \
                       ${NODEGROUP}"

SRC_URI_append = " file://${NODENAME}.service \
                   file://user.config \
                   file://defaults.config \
                   file://release.enit \
                   file://logrotate \
                   file://tmpfiles \
                   "

FILES_${PN}_append = " ${libdir}/tmpfiles.d/${NODENAME}.conf "

SYSTEMD_SERVICE_${PN} = "${NODENAME}.service"

LOGDIR ?= "${localstatedir}/${NODENAME}"

CONFFILES_${PN}_append = " \
    ${sysconfdir}/enit/${NODENAME}/99-user.config \
    ${sysconfdir}/enit/${NODENAME}/00-defaults.config \
    ${sysconfdir}/logrotate.d/${NODENAME} \
"

do_install() {

    # /etc/enit and /var/lib/enit
    install -d ${D}${sysconfdir}/enit/${NODENAME} \
               ${D}${localstatedir}/lib/enit/${NODENAME} \
               ${D}${systemd_unitdir}/system \
               ${D}${libdir}/tmpfiles.d \

    # Logdir
    install -d -m 0775 -o ${NODEUSER} -g ${NODEGROUP} ${D}${LOGDIR}

    # tmpfiles
    install -m 0644 -o "root" -g "root" ${WORKDIR}/tmpfiles ${D}${libdir}/tmpfiles.d/${NODENAME}.conf

    install -m 644 ${WORKDIR}/user.config ${D}${sysconfdir}/enit/${NODENAME}/99-user.config
    install -m 644 ${WORKDIR}/defaults.config ${D}${sysconfdir}/enit/${NODENAME}/00-defaults.config

    install -m 644 ${WORKDIR}/release.enit ${D}${localstatedir}/lib/enit/${NODENAME}

    # logrotate
    install -m 755 -o "root" -g "root" -d ${D}${sysconfdir}/logrotate.d
    install -m 644 ${WORKDIR}/logrotate ${D}${sysconfdir}/logrotate.d/${NODENAME}

    # systemd units
    install -m 644 ${WORKDIR}/*.service ${D}/${systemd_unitdir}/system
}
