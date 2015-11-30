DESCRIPTION = "A webserver for dynamic content written in Erlang"
SECTION = "devel"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5d697daa4658cdb1e2074fd1f1b4f2a4"

SRCREV = "${AUTOREV}"
PV = "git${SRCPV}"
PR = "r0"

SRC_URI = "git://github.com/rickpayne/yaws.git;protocol=https \
           file://dont-use-runtime-tests-for-autoconf.patch \
           file://disable-soap-support.patch \
           file://add-tetrapak.patch"

S = "${WORKDIR}/git"

PACKAGECONFIG ??= "${@base_contains('DISTRO_FEATURES', 'pam', 'pam', '', d)}"
PACKAGECONFIG[pam] = ", --disable-pam, libpam"

inherit autotools tetrapak

do_cleanup() {
    rm -rf ${S}/bin
    rm -f ${S}/src/yaws_soap12_lib.erl
    rm -f ${S}/src/yaws_soap_lib.erl
    rm -f ${S}/src/yaws_soap_srv.erl
    rm -f ${S}/src/yaws_xmlrpc.erl
}
addtask do_cleanup before do_compile after do_configure

python () {
    erlang_def_package("yaws", "yaws-*", "ebin priv", "c_src include src", d)
}
