SUMMARY = "administrative caros user/group (metapackage)"
SECTION = "core"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r1"

SRC_URI += "file://sudo_d_carosadm"

S = "${WORKDIR}"

inherit useradd

# ideally hardcoding uid=200 here would not be necessary
# and handled by the useradd-staticids extension.
# unfortunately this is not declarable within the recipe's
# scope.
# so we maintain meta-caros/files/passwd and still specify
# the static uid here.
#
# useradd-staticids extension code left here for reference
# USERADDEXTENSION = "useradd-staticids"
# USERADD_UID_TABLES = "files/passwd"
# USERADD_GID_TABLES = "files/group"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "-d /home/carosadm -u 200 -g operator -p COfOiy8u70mG6 -c 'CarOS Administrator' -s /bin/zsh carosadm"

GROUPADD_PARAM_${PN} = "-g 201 config"

RDEPENDS_${PN} = "sudo zsh"

CONFFILES_${PN} = "${sysconfdir}/sudoers.d/carosadm"
FILES_${PN} = "/home/carosadm ${sysconfdir}"

do_install () {
	install -m 0755 -d ${D}${sysconfdir}/sudoers.d
	install -m 0640 -g config  ${WORKDIR}/sudo_d_carosadm ${D}${sysconfdir}/sudoers.d/carosadm

	install -m 0755 -o carosadm -d ${D}/home/carosadm
}

PACKAGES = "${PN}"
