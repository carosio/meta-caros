SUMMARY = "ABNF parser generator"
SECTION = "devel"
LICENSE = "other"
LIC_FILES_CHKSUM = "file://src/abnfc.erl;beginline=1;endline=6;md5=c2bc6cc97c392ccf3809c0a167848f11"

PR = "r1"

SRC_URI="git://github.com/heroku/abnfc"
SRCREV="856c77cce8f0dd66bfe95f3ec4041f9f59b742bb"

S = "${WORKDIR}/git"

FILES_${PN} += "${bindir}/abnfc"

inherit rebar
