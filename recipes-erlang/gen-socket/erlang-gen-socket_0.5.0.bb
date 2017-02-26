SUMMARY = "generic socket toolkit"
SECTION = "devel"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://src/gen_socket.erl;beginline=1;endline=30;md5=62fc4961b678e7b93ce9f790eef138ae"

PR = "r1"

SRC_URI = "https://github.com/travelping/gen_socket/archive/${PV}.tar.gz;downloadfilename=gen_socket-${PV}.tar.gz"
SRC_URI[md5sum] = "06d8cb63deba1494883ae4ee2f3f0a64"
SRC_URI[sha256sum] = "da2929138aed6acd6b3c22aced17a9638d4cbc7b8897e2dce7a361ea6aa5babd"

S = "${WORKDIR}/gen_socket-${PV}"
#S = "${WORKDIR}/gen_socket-master"

TETRAPAK_OPTS += "-o build.version ${PV}-${PR}"

inherit tetrapak

python () {
    erlang_def_package("gen-socket", "gen_socket-*", "ebin priv", "src c_src include test README.md NEWS.md rebar.config* .travis.yml", d)
}
