SUMMARY = "An URI-handling library application"
SECTION = "devel"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8b3666f954af52296686a28bc5f337f0"

PR = "r2"

SRC_URI = "git://github.com/heroku/ex_uri.git"
SRCREV="16f26db7026493d2934f615f8884fad42675f00e"

S = "${WORKDIR}/git"
DEPENDS += "erlang-abnfc"

inherit rebar
