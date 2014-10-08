SUMMARY = "Travelping LCM612 command line utility"
DESCRIPTION = "LCM612 command line utility - controlls for a LCM162 from command line, \
usefull for initrd and other restricted setups."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
PR = "r1"

SRC_URI = "file://lcm162.c"

S = "${WORKDIR}"

do_compile() {
	${CC} ${CFLAGS} ${LDFLAGS}  lcm162.c -o  lcm162
}

do_install() {
	install -d ${D}${bindir}
	install -m 0755 lcm162 ${D}${bindir}
}