SUMMARY = "CAROS topmost meta package"
SECTION = "tools"
LICENSE = "CLOSED"

PR = "r1"

PACKAGES = "${PN}"
ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} = " \
    curl \
    iptables \
    less \
    packagegroup-core-ssh-openssh \
    smem-smemcap \
    socat \
    static-routes \
    tcpdump \
    zsh \
"
