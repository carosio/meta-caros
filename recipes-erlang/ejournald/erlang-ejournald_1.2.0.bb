SUMMARY = "Erlang journal binding"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/journald_api.erl;beginline=1;endline=19;md5=1d4630b1ec8e33c2b2016dc223817406"
SRCREV  = "e85b482e787df0e0394efa898c4202213a5d0299"
PR = "r1"
PV = "1.3-development"
SRC_URI = "git://github.com/systemd/ejournald.git;protocol=git" 

S = "${WORKDIR}/git"

DEPENDS = "systemd"

inherit tetrapak

python () {
    erlang_def_package("ejournald", "ejournald-*", "bin ebin priv", "c_src src include rebar.config TODO.rst README.md NEWS.md", d)
}
