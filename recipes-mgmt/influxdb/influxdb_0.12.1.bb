SUMMARY = "InfluxDB - An Open-Source, Distributed, Time Series Database"
DESCRIPTION = "InfluxDB is an open source **distributed time series database** \
with *no external dependencies**. It's useful for recording metrics, \
events, and performing analytics."
SECTION = "net"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/github.com/influxdb/influxdb/LICENSE;md5=ba8146ad9cc2a128209983265136e06a"

PR = "r2"


DEPENDS += "go-cross"

# upstream version
UPV="${PV}"

S = "${WORKDIR}/${PN}-dist-${UPV}"

SRC_URI =  "https://github.com/carosio/influxdb-dist/archive/v${UPV}.tar.gz;downloadfilename=${PN}-${UPV}.tar.gz"
SRC_URI += "file://influxdb.conf"
SRC_URI += "file://influxdb.service"

SRC_URI[md5sum] = "43a7f93acc8aff7dd8788c93d830dd1d"
SRC_URI[sha256sum] = "6edcdcf2557bc2af31bad1a419630bbb75bdd7fe4c44f203a9011c0d6a691f31"


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

inherit caros-service

# FIXME: should to be fixed in go recipe or a class:
export CGO_LDFLAGS="--sysroot=${STAGING_DIR_TARGET}"
export CGO_CFLAGS="--sysroot=${STAGING_DIR_TARGET}"

# systemd units
FILES_${PN} += "${systemd_unitdir}/system/influxdb.service"

# config files
FILES_${PN} += "${sysconfdir}/influxdb.conf"
CONFFILES_${PN} += "${sysconfdir}/influxdb.conf"

INSANE_SKIP_${PN} = "ldflags"
do_compile() {
	env GOPATH=${S} go install -v \
		github.com/influxdb/influxdb/cmd/influxd \
		github.com/influxdb/influxdb/cmd/influx \
		github.com/influxdb/influxdb/cmd/influx_stress \
		github.com/influxdb/influxdb/cmd/influx_inspect
}

do_install() {
	install -d -m 0755 ${D}${bindir}
	install -m 0755 bin/influxd        ${D}${bindir}/
	install -m 0755 bin/influx         ${D}${bindir}/
	install -m 0755 bin/influx_stress  ${D}${bindir}/
	install -m 0755 bin/influx_inspect ${D}${bindir}/

	install -d ${D}${sysconfdir}/influxdb
	install -m 0644 ${WORKDIR}/influxdb.conf ${D}${sysconfdir}/influxdb/.

	install -d ${D}${systemd_unitdir}/system
	install -m 0644 ${WORKDIR}/influxdb.service ${D}${systemd_unitdir}/system/.
}

