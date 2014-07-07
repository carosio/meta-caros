DESCRIPTION = "ETS cache with time to live (TTL)"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=3d36f403d1c4de99731df04918c218aa"

PR = "r1"

SRCREV = "c7a17204cd9602b677540e9c73d10e6f6f7da146"

SRC_URI = "https://github.com/cuongth/ets_cache/archive/${SRCREV}.tar.gz;downloadfilename=ets-cache-${SRCREV}.tar.gz \
           file://config.ini"
SRC_URI[md5sum] = "3c79b43e6b87250c1e56469523394e54"
SRC_URI[sha256sum] = "99c55d9ce497b7d61ba5dd1d6ba92e6a4bb67b0276941a618e59481d32fe3f44"

S = "${WORKDIR}/ets_cache-${SRCREV}"

inherit tetrapak

TETRAPAK_OPTS += "-o build.version ${PV}"

addtask do_prepare_compile after do_patch before do_compile

do_prepare_compile() {
    # add tetrapak
    mkdir -p ${S}/tetrapak
    cp ${WORKDIR}/config.ini ${S}/tetrapak
}

python () {
    erlang_def_package("ets-cache", "ets_cache*", "ebin priv", "src include test", d)
}
