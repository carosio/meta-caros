SUMMARY = "A PBKDF2 implementation for Erlang"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=fb7789d845ace5710d967891fbbdbf93"

PR = "r1"

SRCREV = "f0e27d60524b41b784c4cd591842bdc73e487f82"

SRC_URI = "https://github.com/schnef/erlang-pbkdf2/archive/${SRCREV}.tar.gz;downloadfilename=erlang-pbkdf2-${SRCREV}.tar.gz"

SRC_URI[md5sum] = "32c19c289201709300b8fb040792305e"
SRC_URI[sha256sum] = "9895a5613236f7e05543363327c25158605bf0fe16ee334a21c8fdf83cc7ffe4"

S = "${WORKDIR}/erlang-pbkdf2-${SRCREV}"

inherit rebar
