DESCRIPTION = "Client interface to MongoDB"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=e9afc8235a516dcad63385790d64ab22"

PR = "r1"

SRCREV = "ce9bf77902e837735f3f52b4956eb4abdfa9c928"

SRC_URI = "https://github.com/ChicagoBoss/mongodb-erlang/archive/${SRCREV}.tar.gz;downloadfilename=mongodb-erlang-${SRCREV}.tar.gz \
           file://tetrapakize.patch;apply=yes"

SRC_URI[md5sum] = "dad49044dd8cd812df7ec6f0f749f79c"
SRC_URI[sha256sum] = "75970bf23f936af53949a3cb3777797765557e3b987536d2c23691052b4e4ea0"

S = "${WORKDIR}/mongodb-erlang-${SRCREV}"

inherit tetrapak

DEPENDS += "erlang-bson"
RDEPENDS_${PN} += "erlang-bson"

TETRAPAK_OPTS += "-o build.version ${PV}~~ce9bf77902"

do_patch_append() {
    bb.build.exec_func('do_fix_app_file', d)
}

do_fix_app_file() {
    if test -f ${S}/ebin/mongodb.app ; then
        mv ${S}/ebin/mongodb.app ${S}/src/mongodb.app.src
    fi
}

python () {
    erlang_def_package("mongodb", "mongodb*", "ebin priv", "src include doc Makefile", d)
}
