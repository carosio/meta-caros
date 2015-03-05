DESCRIPTION = "The Click modular router: fast modular packet processing and analysis"
HOMEPAGE = "http://www.read.cs.ucla.edu/click/"
SECTION = "networking"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=4a17a65d5010eaf51b50be71bc4a2e6c"

SRC_URI = "git://github.com/kohler/click.git;protocol=git"
SRCREV = "224a9c05aa7fef6a013f245fac3ec928a4ac0e18"
PV = "git${SRCPV}"
PR = "r1"

S = "${WORKDIR}/git"

DEPENDS = "perl-native libpcap expat"

inherit autotools

EXTRA_OECONF = "--with-expat \
	        --enable-all-elements \
	        --enable-user-multithread"
#
# building the kernel module is badly broken
# last supported kernel seems to be 3.15
#
EXTRA_OECONF += "--disable-linuxmodule"

PACKAGES =+ "${PN}-tools"

FILES_${PN} = "${bindir}/click"

FILES_${PN}-tools = "${bindir}/click-align \
		     ${bindir}/click-combine \
		     ${bindir}/click-flatten \
		     ${bindir}/click-pretty \
		     ${bindir}/click-uncombine \
		     ${bindir}/click-undead \
		     ${bindir}/click-xform \
		     ${bindir}/xml2click \
		     ${bindir}/click2xml"

FILES_${PN}-dev += "${bindir}/click-devirtualize \
		    ${bindir}/click-fastclassifier \
		    ${bindir}/click-buildtool \
		    ${bindir}/click-compile \
		    ${bindir}/click-mkelemmap \
		    ${bindir}/testie \
		    ${bindir}/click-elem2man \
		    ${bindir}/click-mkmindriver \
		    ${bindir}/click-check \
		    ${datadir}/click/mkinstalldirs \
		    ${datadir}/click/elementmap.xml \
		    ${datadir}/click/config.mk \
		    ${datadir}/click/pkg-config.mk \
		    ${datadir}/click/pkg-Makefile \
		    ${datadir}/click/pkg-userlevel.mk \
		    ${datadir}/click/pkg-linuxmodule.mk \
		    ${datadir}/click/pkg-linuxmodule-26.mk \
		    ${datadir}/click/pkg-bsdmodule.mk \
		    ${datadir}/click/srcdir \
		    ${datadir}/click/src"

do_configure() {
    # don't rebuild configure
    oe_runconf
}
