DESCRIPTION = "Revised Erlang PPP Implementation Application"
SECTION = "devel"
LICENSE = "commercial"
LIC_FILES_CHKSUM = "file://${THISDIR}/files/LICENSE;md5=6b53c33414d6498f819ab22fb1d5805b"

PR = "r2"

SRC_URI = "file://user.config \
	   file://defaults.config \
	   file://release.enit \
	   file://run"

S = "${WORKDIR}"
RDEPENDS += "erlang-enit erlang-sasl-syslog erlang-flow-pppctld"

do_install_append() {
    install -m 0755 -d \
	${D}${sysconfdir}/enit/flow-pppctld \
	${D}${localstatedir}/lib/enit/flow-pppctld \
	${D}/var/service/flow-pppctld

    install -m 0644 \
	${S}/defaults.config ${S}/release.enit ${D}${localstatedir}/lib/enit/flow-pppctld
    install -m 0644 \
	${S}/user.config ${D}${sysconfdir}/enit/flow-pppctld
    install -m0755 ${S}/run ${D}/var/service/flow-pppctld
}