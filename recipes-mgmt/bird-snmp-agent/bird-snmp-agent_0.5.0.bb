SUMMARY="snmp-agent for the bird routing daemon"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING.GPLv3;md5=d32239bcb673463ab874e80d47fae504"

PR = "r1"

SRC_URI = "git://github.com/carosio/bird-snmp-agent.git"
SRCREV = "95606e3ccf0894f41642226fd96febd466a0a233"

SRC_URI += "file://bird-snmp-agent.sh"
SRC_URI += "file://bird-snmp-agent.service"

S = "${WORKDIR}/git"

inherit caros-service
SYSTEMD_SERVICE_${PN} += "${PN}.service"

do_install() {
	install -d ${D}${libexecdir}
	install -d ${D}${sbindir}
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${S}/data/BGP4-MIB.txt ${D}${libexecdir}
	install -m 0755 ${S}/bird_bgp.py ${D}${libexecdir}
	install -m 0644 ${S}/birdagent.py ${D}${libexecdir}
	install -m 0644 ${S}/adv_agentx.py ${D}${libexecdir}

	install -m 0755 ${WORKDIR}/bird-snmp-agent.sh ${D}${sbindir}/bird-snmp-agent
	sed --in-place -e "s,@LIBEXEC@,${libexecdir}," ${D}${sbindir}/bird-snmp-agent

	install -m 0644 ${WORKDIR}/bird-snmp-agent.service ${D}${systemd_unitdir}/system/
	sed --in-place -e "s,@SBINDIR@,${sbindir}," ${D}${systemd_unitdir}/system/bird-snmp-agent.service
	sed --in-place -e "s,@LIBEXEC@,${libexecdir}," ${D}${systemd_unitdir}/system/bird-snmp-agent.service
}

