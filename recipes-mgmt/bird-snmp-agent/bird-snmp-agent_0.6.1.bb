SUMMARY="snmp-agent for the bird routing daemon"

LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING.GPLv3;md5=d32239bcb673463ab874e80d47fae504"

PR = "r1"

SRC_URI = "git://github.com/carosio/bird-snmp-agent.git"
SRCREV = "865f8161608f9e9b583b5ecbbacea5d71a2b846f"

SRC_URI += "file://bird-snmp-agent-bgp.sh"
SRC_URI += "file://bird-snmp-agent-ospf.sh"

SRC_URI += "file://bird-snmp-agent.service"
SRC_URI += "file://bird-snmp-agent-bgp.service"
SRC_URI += "file://bird-snmp-agent-ospf.service"

S = "${WORKDIR}/git"

RDEPENDS_${PN} = "python-subprocess python-ctypes python-shell"

inherit caros-service
SYSTEMD_SERVICE_${PN} += "${PN}.service"
SYSTEMD_SERVICE_${PN} += "${PN}-bgp.service"
SYSTEMD_SERVICE_${PN} += "${PN}-ospf.service"

do_install() {
	install -d ${D}${libexecdir}
	install -d ${D}${sbindir}
	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${S}/data/BGP4-MIB.txt ${D}${libexecdir}
	install -m 0644 ${S}/data/OSPF-MIB.txt ${D}${libexecdir}
	install -m 0755 ${S}/bird_bgp.py ${D}${libexecdir}
	install -m 0755 ${S}/bird_ospf.py ${D}${libexecdir}
	install -m 0644 ${S}/birdagent.py ${D}${libexecdir}
	install -m 0644 ${S}/adv_agentx.py ${D}${libexecdir}

	install -m 0755 ${WORKDIR}/bird-snmp-agent-bgp.sh ${D}${sbindir}/bird-snmp-agent-bgp
	install -m 0755 ${WORKDIR}/bird-snmp-agent-ospf.sh ${D}${sbindir}/bird-snmp-agent-ospf
	sed --in-place -e "s,@LIBEXEC@,${libexecdir}," \
		${D}${sbindir}/bird-snmp-agent-bgp \
		${D}${sbindir}/bird-snmp-agent-ospf

	# service files
	install -m 0644 ${WORKDIR}/bird-snmp-agent.service ${D}${systemd_unitdir}/system/
	install -m 0644 ${WORKDIR}/bird-snmp-agent-bgp.service ${D}${systemd_unitdir}/system/
	install -m 0644 ${WORKDIR}/bird-snmp-agent-ospf.service ${D}${systemd_unitdir}/system/

	sed --in-place -e "s,@SBINDIR@,${sbindir}," \
		${D}${systemd_unitdir}/system/bird-snmp-agent-bgp.service \
		${D}${systemd_unitdir}/system/bird-snmp-agent-ospf.service

	sed --in-place -e "s,@LIBEXEC@,${libexecdir}," \
		${D}${systemd_unitdir}/system/bird-snmp-agent-bgp.service \
		${D}${systemd_unitdir}/system/bird-snmp-agent-ospf.service
}

