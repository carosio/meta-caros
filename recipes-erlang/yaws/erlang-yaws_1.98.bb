DESCRIPTION = "A webserver for dynamic content written in Erlang"
SECTION = "devel"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5d697daa4658cdb1e2074fd1f1b4f2a4"

PR = "r1"

SRC_URI = "https://github.com/rickpayne/yaws/archive/yaws-${PV}.tar.gz;downloadfilename=${PN}-${PV}.tar.gz"
SRC_URI += "file://dont-use-runtime-tests-for-autoconf.patch"
SRC_URI += "file://disable-soap-support.patch"
SRC_URI += "file://add-tetrapak.patch"

SRC_URI[md5sum] = "a1b3717620021554f07bfaa82fedaa33"
SRC_URI[sha256sum] = "0507dde2c1ced6f62450b2477d2b1bfc695f1bb900a855faa0abaf3e103984c1"

S = "${WORKDIR}/yaws-yaws-${PV}"


PACKAGECONFIG ??= "${@base_contains('DISTRO_FEATURES', 'pam', 'pam', '', d)}"
PACKAGECONFIG[pam] = ", --disable-pam, libpam"

inherit autotools-brokensep tetrapak

TETRAPAK_OPTS += "-o build.version ${PV}"

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
