SUMMARY = "Erlang journal binding"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/journald_api.erl;beginline=1;endline=19;md5=1d4630b1ec8e33c2b2016dc223817406"
SRCREV  = "0d60cf7798358738886d01fa6ff33882dc581cd7"

PR = "r1"

SRC_URI = "git://github.com/systemd/ejournald.git;protocol=git"
SRC_URI += "file://0001-adapt-Makefile-to-caros-systemd.patch;apply=yes"


S = "${WORKDIR}/git"

DEPENDS = "systemd"

inherit tetrapak

python () {
    erlang_def_package("ejournald", "ejournald-*", "bin ebin priv", ".pc c_src src include rebar.config TODO.rst README.md NEWS.md", d)
}
