SUMMARY = "CAROS topmost meta package"
SECTION = "tools"
LICENSE = "CLOSED"

PR = "r5"

PACKAGES = "${PN}"
ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} = " \
    less \
    zsh \
    caros-networking \
"
