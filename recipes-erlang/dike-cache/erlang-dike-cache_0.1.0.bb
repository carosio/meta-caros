DESCRIPTION = "dike cache"
SECTION = "net"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=65d26fcc2f35ea6a181ac777e42db1ea"

PR = "r1"

SRC_URI = "https://github.com/travelping/dike_cache/archive/${PV}.tar.gz;downloadfilename=${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "4c0df3dfe09cc033b304977b2a388394"
SRC_URI[sha256sum] = "2d7b701448581d99b44e9d60354efda3c319528e4316940e7f7c3bc168c43f1d"

S = "${WORKDIR}/dike_cache-${PV}"

DEPENDS_append = " erlang-dike "
RDEPENDS_${PN}_appends = " erlang-dike "

inherit tetrapak

python () {
                    erlang_def_package("dike-cache", "dike_cache-*", "ebin priv", "src test include LICENSE NEWS.md README.md rebar.config", d)
}
