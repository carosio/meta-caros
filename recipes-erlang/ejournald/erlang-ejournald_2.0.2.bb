SUMMARY = "Erlang journal binding"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/journald_api.erl;beginline=1;endline=19;md5=1d4630b1ec8e33c2b2016dc223817406"
SRCREV  = "73cd53bff08a02fd6e46afb91dd6dfe282a599b3"

PR = "r0"

SRC_URI = "git://github.com/systemd/ejournald.git;protocol=git"

S = "${WORKDIR}/git"

DEPENDS = "systemd"

inherit tetrapak

python () {
    erlang_def_package("ejournald", "ejournald-*", "bin ebin priv", ".pc c_src src include rebar.config TODO.rst README.md NEWS.md", d)
}
