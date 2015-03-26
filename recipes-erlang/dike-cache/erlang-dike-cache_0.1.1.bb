SUMMARY = "dike cache"
SECTION = "net"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=65d26fcc2f35ea6a181ac777e42db1ea"

PR = "r2"

SRC_URI = "https://github.com/travelping/dike_cache/archive/${PV}.tar.gz;downloadfilename=${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "34036244629ad02278371d6b6da785da"
SRC_URI[sha256sum] = "60abede5c49c8d996a146aa5cac4edee1c81766c8323ceededd2f1d4f07f0cb9"

S = "${WORKDIR}/dike_cache-${PV}"

DEPENDS_append = " erlang-dike "
RDEPENDS_${PN}_appends = " erlang-dike "

inherit tetrapak

TETRAPAK_OPTS += "-o build.version ${PV}"

python () {
                    erlang_def_package("dike-cache", "dike_cache-*", "ebin priv", "src test include LICENSE NEWS.md README.md rebar.config", d)
}
