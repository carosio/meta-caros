SUMMARY = "Common Interface to Erlang HTTP Servers"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://MIT-LICENSE;md5=da9c49784d0b343ab8f8196dd3880ef4"

PR = "r1"

SRCREV_ORIG = "${PV}"
SRCREV = "v${SRCREV_ORIG}"

SRC_URI = "https://github.com/nitrogen/simple_bridge/archive/${SRCREV}.tar.gz;downloadfilename=simple_bridge-${SRCREV_ORIG}.tar.gz"
SRC_URI[md5sum] = "ba3f8393a49a34c8e7960515ff875f58"
SRC_URI[sha256sum] = "3414428def32f7a70c4486b64da727190d0c9122cfe8a7cd835b132c7af16a09"

S = "${WORKDIR}/simple_bridge-${SRCREV_ORIG}"

TETRAPAK_OPTS += "-o build.version ${PV}"
TETRAPAK_OPTS += "-o package.maintainer 'Travelping GmbH <info@travelping.com>'"
TETRAPAK_OPTS += "-o package.exclude '^\.gitignore|^\.gitmodules|^CONTRIB\.markdown|^data|^Makefile|^MIT-LICENSE|^README\.markdown|^rebar|^rebar\.config|^rebar\.test\.config'"

inherit tetrapak

DEPENDS += "erlang-cowboy erlang-misultin erlang-mochiweb erlang-mimetypes erlang-pmod-transform"
RDEPENDS_${PN} += "erlang-mimetypes"

addtask do_prepare_compile after do_patch before do_compile

do_prepare_compile() {
    # remove yaws support
    rm -rf ${S}/src/yaws_bridge_modules

    # remove webmachine support
    rm -rf ${S}/src/webmachine_bridge_modules
}

python () {
    erlang_def_package("simple-bridge", "simple_bridge*", "ebin", "include src test", d)
}
