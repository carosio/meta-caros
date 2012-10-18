DESCRIPTION = "Revised OpenFlow Library ETHCTLD (ROFL)"
SECTION = "net"
LICENSE = "commercial"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=e4d7e1de5b6d0c6597a7f5cb61577266"

DEPENDS = "rofl-core"

PR = "r0"

SRC_URI = "git://gitolite@codebasin.net/ethctld.git;protocol=ssh;tag=v${PV} \
	   file://init"

S = "${WORKDIR}/git"

inherit autotools update-rc.d

INITSCRIPT_NAME = "ethctld"
INITSCRIPT_PARAMS = "defaults 50"

EXTRA_OECONF += "--enable-debug"
CXXFLAGS_append = " -I${STAGING_INCDIR}/rofl"

do_install_append() {
    install -d ${D}${sysconfdir} \
	${D}${sysconfdir}/init.d \
	${D}${sysconfdir}/rofl

    install -m0755 ${WORKDIR}/init ${D}${sysconfdir}/init.d/ethctld
}
