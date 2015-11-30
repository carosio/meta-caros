SUMMARY = "generic socket toolkit"
SECTION = "devel"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://src/gen_socket.erl;beginline=1;endline=30;md5=62fc4961b678e7b93ce9f790eef138ae"

PR = "r0"

SRC_URI = "https://github.com/travelping/gen_socket/archive/master.tar.gz;downloadfilename=gen_socket-${PV}.tar.gz"
SRC_URI[md5sum] = "b48f5b1257276aef3c2598a3d0572606"
SRC_URI[sha256sum] = "72c05886d5c00a5fd3b501f0810ee6224be4455881938a25a28887a3c1ab1d8f"

#S = "${WORKDIR}/gen_socket-${PV}"
S = "${WORKDIR}/gen_socket-master"

TETRAPAK_OPTS += "-o build.version ${PV}-${PR}"

inherit tetrapak

python () {
    erlang_def_package("gen-socket", "gen_socket-*", "ebin priv", "src c_src include test README.md NEWS.md rebar.config* .travis.yml", d)
}
