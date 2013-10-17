DESCRIPTION = "common lib"
SECTION = "net"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/common_lib.erl;beginline=1;endline=28;md5=9ce15051e0553845ff2a71e49388ffcb"

SRCREV="a3d6313e888068c39ab5606605a6a6c388423d6c"
PR = "r1"

#erllibs = "${STAGING_DIR_ERLANG_LIBS}:${TETRAPAK_ERL_LIBS}"

SRC_URI = "git://git@git.tpip.net/common_lib.git;protocol=ssh \
           file://tetrapakize.patch;apply=yes"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("common-lib", "cl_* common_lib*", "ebin", "src doc", d)
}

#do_compile_prepend() {
#    # The Makefile does not create the ebin/ directory itself
#    install -d ${S}/ebin 
#}

#do_install_append() {
#    install -d ${D}${libdir}/erlang/lib/${PN}-${PV}/ebin
#    install -m 755 ${S}/ebin/* ${D}${libdir}/erlang/lib/${PN}-${PV}/ebin
#}
