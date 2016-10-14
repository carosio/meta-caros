SUMMARY = "caros user/group for caros service (metapackage)"
SECTION = "core"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PR = "r1"

S = "${WORKDIR}"

inherit useradd

USERNAME_${PN} ??= "carosapp"
GROUPNAME_${PN} ??= "carosapp"

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
USERADD_PARAM_${PN} = "-u 300 -g ${GROUPNAME_${PN}} -c 'Application User' ${USERNAME_${PN}}"

GROUPADD_PARAM_${PN} = "-g 301 ${GROUPNAME_${PN}}"

PACKAGES = "${PN}"

pkg_postinst_${PN}_append () {
  groupadd -f -g 250 config
  gpasswd -a ${USERNAME_${PN}} config
}

ALLOW_EMPTY_${PN}="1"
