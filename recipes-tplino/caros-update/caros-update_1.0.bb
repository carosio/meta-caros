DESCRIPTION = "CAROS Update System"
SECTION = "console/utils"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

SRC_URI += " \
    file://caros-offline-update.sh \
    file://caros-update.sh \
    file://caros-offline-update.service \
"

DEPENDS = "systemd"
RDEPENDS_${PN} = "systemd util-linux-mount util-linux-unshare"

PACKAGES = "${PN}"

FILES_${PN} = "\
    ${libexecdir}/caros-offline-update \
    ${sbindir}/caros-update \
    ${systemd_unitdir}/system/*.service \
    ${localstatedir}/lib/caros-offline-update \
"

do_install() {
    install -d ${D}${libexecdir} \
    	       ${D}${sbindir} \
    	       ${D}${systemd_unitdir}/system \
	       ${D}${localstatedir}/lib/caros-offline-update
    install -m 0644 ${WORKDIR}/caros-offline-update.service ${D}${systemd_unitdir}/system
    install -m 0755 ${WORKDIR}/caros-offline-update.sh ${D}${libexecdir}/caros-offline-update
    install -m 0755 ${WORKDIR}/caros-update.sh ${D}${sbindir}/caros-update

    sed -i -e "s:#STATEDIR#:${localstatedir}:g" \
           -e "s:#LIBEXECDIR#:${libexecdir}:g" \
	${D}${systemd_unitdir}/system/*.service \
    	${D}${libexecdir}/caros-offline-update \
	${D}${sbindir}/caros-update
}


pkg_postinst_${PN}() {
OPTS=""

if [ -n "$D" ]; then
    OPTS="--root=$D"
fi

if type systemctl >/dev/null 2>/dev/null; then
        systemctl $OPTS enable caros-offline-update.service &> /dev/null || :
fi
}

pkg_prerm_${PN}() {
OPTS=""

if [ -n "$D" ]; then
    OPTS="--root=$D"
fi

if type systemctl >/dev/null 2>/dev/null; then
        systemctl $OPTS disable caros-offline-update.service &> /dev/null || :
fi
}
