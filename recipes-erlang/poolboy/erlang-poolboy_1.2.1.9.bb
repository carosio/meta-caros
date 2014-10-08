DESCRIPTION = "A hunky Erlang worker pool factory"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3bfd34238ccc26128aef96796a8bbf97"

PR = "r0.2"

SRCREV = "91cf587b6029be696679a4788905de796ec89df3"

SRC_URI = "https://github.com/devinus/poolboy/archive/${SRCREV}.tar.gz;downloadfilename=poolboy-${SRCREV}.tar.gz \
           file://tetrapakize.patch"

SRC_URI[md5sum] = "545e668c55978a1a0ef1db9035315610"
SRC_URI[sha256sum] = "4c4766611aae6d742ca5c541fdb2026035058757255bc362dacb636747c5ad14"

S = "${WORKDIR}/poolboy-${SRCREV}"

inherit tetrapak

TETRAPAK_OPTS += "-o build.version ${PV}"

python () {
    erlang_def_package("poolboy", "poolboy*", "ebin priv", "src include test", d)
}
