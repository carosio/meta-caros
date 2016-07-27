SUMMARY = "Exometer InfluxDB reporter"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=b933866ccbb932327446d700f06847fc"

PR = "r2"

SRC_URI = "git://github.com/travelping/exometer_influxdb \
	   file://config.ini"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"

inherit tetrapak

DEPENDS += "erlang-hackney erlang-lager erlang-exometer-core"

addtask do_prepare_compile after do_patch before do_compile

do_prepare_compile() {
    mkdir -p ${S}/tetrapak
    cp ${WORKDIR}/config.ini ${S}/tetrapak
    rm ${S}/LICENSE
}

python () {
    erlang_def_package("exometer-influxdb", "exometer_influxdb*", "ebin", "include src test", d)
}
