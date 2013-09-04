DESCRIPTION = "autossh"
LICENSE = "GPLv2+"
PR = "r9"

LIC_FILES_CHKSUM = "file://../git/autossh.c;beginline=1;endline=24;md5=45e6f189cd3b4d0d119c0946df4071fd"

S = "${WORKDIR}/git"

RDEPENDS_${PN} = "dropbear"

SRC_URI = " \
    git://github.com/jonhiggs/autossh.git;protocol=git \
    file://autossh.service \
    file://autossh_env \
    file://autossh.sh"

SRCREV="9c72d3b6f77bfe2ad57844128ea46019fecb7fdb"

PV-orig := "${PV}" 
PV = "${PV-orig}-${PR}-${SRCPV}"

inherit autotools systemd 

SYSTEMD_SERVICE_${PN} = "autossh.service"

do_install() {
    install -d ${D}${sysconfdir} ${D}${sysconfdir}/init.d ${D}${bindir} ${D}${systemd_unitdir}/system ${D}${ROOT_HOME}
    install -d -m 700 ${D}${sysconfdir}/autossh
    install -m 0644 ${WORKDIR}/autossh.service ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/autossh_env ${D}${sysconfdir}/autossh
    install -m 0755 ${S}/autossh ${D}${bindir}/autossh
    install -m 0755 ${WORKDIR}/autossh.sh ${D}${sysconfdir}/init.d/
}

FILES_${PN} = "${bindir}/autossh \
    ${systemd_unitdir}/system/autossh.service \
    ${bindir} \
    ${systemd_unitdir}/system \
    ${ROOT_HOME} \
    /etc/autossh \
    ${sysconfdir}/autossh/autossh_env \
    ${sysconfdir}/init.d/autossh.sh"

CONFFILES_${PN} += "${sysconfdir}/autossh/autossh_env"
SRC_URI[md5sum] = "26520eea934f296be0783dabe7fcfd28"
SRC_URI[sha256sum] = "6fcaba6a409a46bdf832086736bb8f09d245ebce11027f41d39588a95dc7fd1d"
