SUMMARY = "Universal Node orchestrator (un-orchestrator)"
DESCRIPTION = "The Universal Node orchestrator (un-orchestrator) is the master of the universal node."
HOMEPAGE = "https://github.com/bisdn/un-orchestrator"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=d1689c29f19f4d5193c60183c28845af"
SECTION = "console/tools"

SRC_URI = "git://github.com/bisdn/un-orchestrator.git"
SRCREV = "0d6e51ff67f3e688beb7c1a2aee86afdaa435fa6"

DEPENDS = "boost json-spirit libmicrohttpd libvirt openvswitch rofl-core"

S = "${WORKDIR}/git"

OECMAKE_SOURCEPATH = "${S}/orchestrator"

inherit cmake

do_configure() {
        # Ensure we get the cmake configure and not qmake
        cmake_do_configure
}
