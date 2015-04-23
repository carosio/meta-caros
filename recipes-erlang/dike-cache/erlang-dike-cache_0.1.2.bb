SUMMARY = "Distributed consistent cache based on dike and paxos"
SECTION = "net"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=65d26fcc2f35ea6a181ac777e42db1ea"

PR = "r1"

SRC_URI = "https://github.com/travelping/dike_cache/archive/${PV}.tar.gz;downloadfilename=${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "9baed6427339a83e30731fc82a861c39"
SRC_URI[sha256sum] = "daff976a99f010b0e07e19e7d0be2227d863163e2b229dfaac3a3c844188f32b"

S = "${WORKDIR}/dike_cache-${PV}"

DEPENDS_append = " erlang-dike "
RDEPENDS_${PN}_appends = " erlang-dike "

inherit tetrapak

TETRAPAK_OPTS += "-o build.version ${PV}"

python () {
                    erlang_def_package("dike-cache", "dike_cache-*", "ebin priv", "src test include LICENSE NEWS.md README.md rebar.config", d)
}
