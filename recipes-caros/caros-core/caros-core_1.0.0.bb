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
    socat \
    tcpdump \
    zsh \
    packagegroup-core-ssh-openssh \
    smem-smemcap \
"
