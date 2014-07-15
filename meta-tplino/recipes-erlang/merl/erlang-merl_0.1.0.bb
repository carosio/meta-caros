DESCRIPTION = "Metaprogramming in Erlang"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

PR = "r1"

SRCREV = "0edc0530dfdcdcd5765a574235215826ed958957"

SRC_URI = "https://github.com/erlydtl/merl/archive/${SRCREV}.tar.gz;downloadfilename=merl-${SRCREV}.tar.gz \
           file://build.erl"
SRC_URI[md5sum] = "4a81fcc6ecdef5879646c35968a72d0f"
SRC_URI[sha256sum] = "48c822e131e6d98288ddbd12cb770900aea4b532778058d4eec0d13d54cb926c"

S = "${WORKDIR}/merl-${SRCREV}"

TETRAPAK_OPTS += "-o build.version ${PV}~~0edc053"
TETRAPAK_OPTS += "-o package.maintainer 'Travelping GmbH <info@travelping.com>'"
TETRAPAK_OPTS += "-o package.exclude '^doc|^LICENSE'"

inherit tetrapak

addtask do_prepare_compile after do_patch before do_compile

do_prepare_compile() {
    # add tetrapak
    mkdir -p ${S}/tetrapak
    cp ${WORKDIR}/build.erl ${S}/tetrapak
}

python () {
    erlang_def_package("merl", "merl*", "ebin priv", "examples include README.md src Makefile", d)
}
