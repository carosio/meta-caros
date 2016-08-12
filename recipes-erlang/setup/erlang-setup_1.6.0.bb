SUMMERY = "The setup application"
DESCRIPTION = "setup"
SECTION = "devel"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"

PR="r1"

SRC_URI = "git://github.com/uwiger/setup.git;protocol=git"
SRCREV = "5c7d635145e5ffb8b0fa6b470770cdd1cb16b0b2"

S ="${WORKDIR}/git"

DEPENDS += "erlang-edown"
RDEPENDS_${PN} += "erlang-edown"

inherit rebar
