DESCRIPTION = "A webserver for dynamic content written in Erlang"
SECTION = "devel"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5d697daa4658cdb1e2074fd1f1b4f2a4"

PR = "r1"

SRC_URI = "https://github.com/klacke/yaws/archive/yaws-${PV}.tar.gz;downloadfilename=${PN}-${PV}.tar.gz"
SRC_URI += "file://disable-soap-support.patch"
SRC_URI += "file://add-tetrapak.patch"

SRC_URI[md5sum] = "b706e721fe352a70c978c5c3eaed02de"
SRC_URI[sha256sum] = "d2f5faf57b5087f3f0d8fc085a2342793db0f63007ddeb69e5c11935ef773729"

S = "${WORKDIR}/yaws-yaws-${PV}"


PACKAGECONFIG ??= "${@base_contains('DISTRO_FEATURES', 'pam', 'pam', '', d)}"
PACKAGECONFIG[pam] = ", --disable-pam, libpam"

inherit autotools-brokensep tetrapak

TETRAPAK_OPTS += "-o build.version ${PV}"

FILES_${PN}-dbg += " /usr/lib/erlang/lib/yaws-${PV}/c_src/.libs/.debug"

do_cleanup() {
    rm -rf ${S}/bin
    rm -f ${S}/src/yaws_soap12_lib.erl
    rm -f ${S}/src/yaws_soap_lib.erl
    rm -f ${S}/src/yaws_soap_srv.erl
    rm -f ${S}/src/yaws_xmlrpc.erl
}
addtask do_cleanup before do_compile after do_configure

do_install_append() {
    rm -f ${D}/usr/lib/erlang/lib/yaws-${PV}/*-libtool
}

python () {
    erlang_def_package("yaws", "yaws-*", "ebin priv", "c_src include src ac-aux .travis.yml erlang_deps.mk aclocal.m4", d)
}
