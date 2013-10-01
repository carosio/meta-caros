DESCRIPTION = "Erlang ZeroMQ Driver"
SECTION = "devel"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=cde9803a34b8d019cb003b557f9ec24f"

SRCREV="30249a36b39482a98772c881334ee703715a54cc"
PR = "r1"

SRC_URI = "git://github.com/zeromq/erlzmq2.git;protocol=git \
	   file://add-tetrapak.patch;apply=yes"

S = "${WORKDIR}/git"

DEPENDS += "zeromq"

inherit tetrapak

python () {
    erlang_def_package("erlzmq", "erlzmq-*", "ebin priv", "c_src src include perf test README.md LICENSE", d)
}
