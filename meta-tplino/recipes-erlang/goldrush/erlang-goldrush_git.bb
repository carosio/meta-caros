DESCRIPTION = "Small, Fast event processing and monitoring for Erlang/OTP applications."
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7b9f7f44f40906370137171b3437c818"

SRCREV = "7ff9b03e4038e83732b241d92ccee2322c4ece5e"
PR = "r0"

SRC_URI = "git://github.com/DeadZen/goldrush.git;protocol=git \
	   file://add-tetrapak.patch;apply=yes"

DEPENDS_append = ""
RDEPENDS_${PN}_append = ""

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("goldrush", "goldrush-*", "ebin", "src LICENSE README.md", d)
}
