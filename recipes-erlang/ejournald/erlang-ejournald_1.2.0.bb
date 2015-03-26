SUMMARY = "Erlang journal binding"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://src/journald_api.erl;beginline=1;endline=19;md5=68c35caa2bea4511f71968b6fe5921d6"
SRCREV  = "d464bda604d4717787aa2aa13d2e4ad943fdd87b"
PR = "r0"
SRC_URI = "git://github.com/travelping/ejournald.git;protocol=git" 

S = "${WORKDIR}/git"

DEPENDS = "systemd"

inherit tetrapak

python () {
    erlang_def_package("ejournald", "ejournald-*", "bin ebin priv", "c_src src include TODO.rst README.rst NEWS.md", d)
}
