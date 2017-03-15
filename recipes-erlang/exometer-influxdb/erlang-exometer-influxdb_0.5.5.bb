SUMMARY = "Exometer InfluxDB reporter"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=800f96d7303a1e2a8ed5d2aec36ee1a7"

PR = "r1"

SRC_URI = "git://github.com/travelping/exometer_influxdb \
	   file://config.ini"
SRCREV = "6aa389b5d36d3b0935ab4f77fb65f96e0dd18286"

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
