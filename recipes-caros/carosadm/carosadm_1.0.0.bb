SUMMARY = "administrative caros user/group (metapackage)"
SECTION = "core"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r2"

SRC_URI += "file://sudo_d_carosadm"

S = "${WORKDIR}"

inherit useradd

ADMUSERNAME ??= "carosadm"
ADMPASSWORDHASH ??= "COfOiy8u70mG6"

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
USERADD_PARAM_${PN} = "-d /home/${ADMUSERNAME} -u 200 -g operator -p ${ADMPASSWORDHASH} -c 'Administrative User' -s /bin/zsh ${ADMUSERNAME}"

GROUPADD_PARAM_${PN} = "-g 201 config"

RDEPENDS_${PN} = "sudo zsh"

CONFFILES_${PN} = "${sysconfdir}/sudoers.d/adm"
FILES_${PN} = "/home/${ADMUSERNAME} ${sysconfdir}"

do_install () {
	install -m 0755 -d ${D}${sysconfdir}/sudoers.d
	install -m 0640 -g config  ${WORKDIR}/sudo_d_carosadm ${D}${sysconfdir}/sudoers.d/adm

	install -m 0755 -o ${ADMUSERNAME} -d ${D}/home/${ADMUSERNAME}
}

PACKAGES = "${PN}"
