DESCRIPTION = "test server for hello"
SECTION = "net"
LICENSE = "CLOSED"

inherit mix

PR = "r1"
REL_NAME = "hello_test_server"
REL_VSN = "${PV}"

SRC_URI += "git://github.com/travelping/hello_test_server.git;name=${REL_NAME};destsuffix=git-${REL_NAME}"

SRCREV_${REL_NAME} = "b2cb5b496006b06fc75ff4ca4588b80d1b2c516c"

S = "${WORKDIR}/git-${REL_NAME}"