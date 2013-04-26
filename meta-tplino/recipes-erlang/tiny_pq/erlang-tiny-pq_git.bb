CRIPTION = "TinyPQ: A simple priority queue based on gb_trees"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=6f1e54f6e28cd323e6407b775de4fe1e"

SRCREV = "a3f8a6b0fe89c2f32645c220ee2a4f9d7f2998f0"
PR = "r0"

SRC_URI = "git://git@git.tpip.net/tiny_pq;protocol=ssh"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("tiny-pq", "tiny_pq*", "ebin priv", "src include", d)
}
