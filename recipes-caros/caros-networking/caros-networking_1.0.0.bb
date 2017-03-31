SUMMARY = "CAROS networking meta package"
SECTION = "tools"
LICENSE = "CLOSED"

PR = "r1"

PACKAGES = "${PN}"
ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN} = " \
    curl \
    iptables \
    kernel-module-br-netfilter \
    kernel-module-iptable-filter \
    kernel-module-iptable-nat \
    kernel-module-nf-nat-ipv4 \
    kernel-module-veth \
    kernel-module-xt-addrtype \
    kernel-module-xt-conntrack \
    kernel-module-xt-nat \
    packagegroup-core-ssh-openssh \
    smem-smemcap \
    socat \
    static-routes \
    tcpdump \
    keepalived \
"
