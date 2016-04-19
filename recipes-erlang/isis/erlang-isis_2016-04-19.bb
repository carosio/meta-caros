DESCRIPTION = "IS-IS implementation in Erlang"
SECTION = "network"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRCREV = "67f07c2a96620ee1eab6bbf0626f198804c7da58"

SRC_URI = "git://github.com/carosio/isis.git;protocol=https;branch=unify"

PR="r1"

DEPENDS += "erlang-lager erlang-eenum erlang-procket erlang-cowboy"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("isis", "isis-*", "ebin priv", "src include", d)
}
