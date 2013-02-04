DESCRIPTION = "Erlang is a programming language used to build massively scalable soft real-time systems with requirements on high availability."
HOMEPAGE = "http://www.erlang.org/"
SECTION = "devel"
LICENSE = "EPL-1.1"
LIC_FILES_CHKSUM = "file://EPLICENCE;md5=09f9063ea35bc5bd124df2fda1d9d2c7"

PR = "r0"

SRC_URI = "http://www.erlang.org/download/otp_src_${PV}.tar.gz"

SRC_URI[md5sum] = "f12d00f6e62b36ad027d6c0c08905fad"
SRC_URI[sha256sum] = "f94f7de7328af3c0cdc42089c1a4ecd03bf98ec680f47eb5e6cddc50261cabde"

S = "${WORKDIR}/otp_src_${PV}"

DEPENDS += "openssl"

inherit autotools native

EXTRA_OECONF = '\
	     --enable-smp-support \
	     --without-java \
	     --with-ssl \
'

do_configure() {
        oe_runconf
}
