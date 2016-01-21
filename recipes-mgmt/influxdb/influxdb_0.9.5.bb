SUMMARY = "InfluxDB - An Open-Source, Distributed, Time Series Database"
DESCRIPTION = "InfluxDB is an open source **distributed time series database** \
with *no external dependencies**. It's useful for recording metrics, \
events, and performing analytics."
SECTION = "net"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/github.com/influxdb/influxdb/LICENSE;md5=b7e66d3c3a8effee059f8ec3b62b6a18"

PR = "r1"


DEPENDS += "golang-cross"

# upstream version
UPV="${PV}-rc2"

S = "${WORKDIR}/${PN}-dist-${UPV}"

SRC_URI =  "https://github.com/carosio/influxdb-dist/archive/v${UPV}.tar.gz;downloadfilename=${PN}-${UPV}.tar.gz"
SRC_URI += "file://influxdb.conf"
SRC_URI += "file://influxdb.service"

SRC_URI[md5sum] = "4d2a47010e1990e74c88e56c941d863e"
SRC_URI[sha256sum] = "e2cf6075e4f6fecab806dc1d1a10f069824435bdadb063ef9a6bb8092eaae144"


INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

inherit caros-service


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

