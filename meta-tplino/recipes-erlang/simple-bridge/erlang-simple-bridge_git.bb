DESCRIPTION = "Common Interface to Erlang HTTP Servers"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://MIT-LICENSE;md5=da9c49784d0b343ab8f8196dd3880ef4"

SRCREV="b74f97c68fc5521947ef6b8f2494c448994bef52"
PR = "r0"

SRC_URI = "git://git@git.tpip.net/simple_bridge.git;protocol=ssh"

S = "${WORKDIR}/git"

inherit tetrapak

DEPENDS_append = " erlang-cowboy erlang-misultin erlang-mochiweb erlang-mimetypes erlang-pmod-transform "
RDEPENDS_${PN}_append = " erlang-mimetypes erlang-pmod-transform "

python () {
    erlang_def_package("simple-bridge", "simple_bridge*", "ebin priv", "src include test data", d)
}
