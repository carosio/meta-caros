DESCRIPTION = "An erlang application for consuming, producing and manipulating json. inspired by yajl"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b88dbbdb4895db36422fd59fca600660"

PR = "r1"

SRC_URI = "https://github.com/talentdeficit/jsx/archive/v${PV}.tar.gz"
SRC_URI[md5sum] = "371b1a3495e563805962bd342a2af535"
SRC_URI[sha256sum] = "f502bb624a6cea04892606bc1b6a895ebcf3315c93e84056a5b797b92e56fc00"

S = "${WORKDIR}/jsx-${PV}"

inherit tetrapak

python () {
    erlang_def_package("jsx", "jsx*", "ebin priv", "src include .gitignore .travis.yml CHANGES.md LICENSE README.md hipe.cfg jsxbench rebar.config", d)
}
