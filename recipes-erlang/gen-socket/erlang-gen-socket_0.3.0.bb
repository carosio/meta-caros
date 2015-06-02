SUMMARY = "generic socket toolkit"
SECTION = "devel"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://src/gen_socket.erl;beginline=1;endline=30;md5=62fc4961b678e7b93ce9f790eef138ae"

SRC_URI = "git://github.com/travelping/gen_socket.git"
SRCREV = "a19559e91916f842c2b85b23b78d30d398d53a4d"

PR = "r2"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("gen-socket", "gen_socket-*", "ebin priv", "src c_src include test README.md NEWS.md", d)
}
