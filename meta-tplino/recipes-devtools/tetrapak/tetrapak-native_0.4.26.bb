DESCRIPTION = "tetrapak is an extensible build system for Erlang/OTP applications."
SCTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/tetrapak.erl;beginline=1;endline=19;md5=300d83493c235b71e1a4d58e25379bc5"

SRCREV = "${PV}"
PR = "r1"

SRC_URI = "https://github.com/travelping/tetrapak/archive/${SRCREV}.tar.gz;downloadfilename=tetrapak-${SRCREV}.tar.gz \
           file://plugin_auto_search_false.patch;apply=yes"
SRC_URI[md5sum] = "47008bc56816ad2490e9fadc1bbb9a90"
SRC_URI[sha256sum] = "edd7c4205803fc34ad82fd2d33ccc7ca2c6e508231c84ab1fd7bd23908500338"

S = "${WORKDIR}/tetrapak-${SRCREV}"

inherit native erlangnative

do_install() {
    install -d -m755 ${D}${libdir}/erlang/lib/tetrapak
    install -d -m755 ${D}${bindir}
    cp -a ${S}/* ${D}${libdir}/erlang/lib/tetrapak
    ln -sf ../lib/erlang/lib/tetrapak/bin/tetrapak ${D}${bindir}/tetrapak
}
