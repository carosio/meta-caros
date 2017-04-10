SUMMARY = "generic socket toolkit"
SECTION = "devel"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://src/gen_socket.erl;beginline=1;endline=30;md5=62fc4961b678e7b93ce9f790eef138ae"

PR = "r1"

SRC_URI = "https://github.com/travelping/gen_socket/archive/${PV}.tar.gz;downloadfilename=gen_socket-${PV}.tar.gz"
SRC_URI[md5sum] = "1152bde65aeebf8a1ced4bfb4305f5fc"
SRC_URI[sha256sum] = "644c8c93a8202bde0bd42ca68cec772568707c3d88205bdc97034336ac0c0dae"

S = "${WORKDIR}/gen_socket-${PV}"

TETRAPAK_OPTS += "-o build.version ${PV}-${PR}"

inherit tetrapak

python () {
    erlang_def_package("gen-socket", "gen_socket-*", "ebin priv", "src c_src include test README.md NEWS.md rebar.config* .travis.yml", d)
}
