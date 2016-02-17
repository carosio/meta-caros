DESCRIPTION = "CAROS main packagegroup"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PR = "r5"

inherit packagegroup

PACKAGES = "${PN}"
PACKAGE_GROUP_caros = "${PN}"

RDEPENDS_${PN} = "\
caros-core \
packagegroup-caros-ansible-deps \
packagegroup-caros-erlang \
packagegroup-caros-systemtools \
packagegroup-caros-virtualization \
unimux \
chello \
wget vim tmux \
strace pv \
journal-gateway-gelf journal-gateway-zmtp erlang-lager-journald-backend jlog \
nfs-utils \
unzip \
unbound nsd \
kellner \
influxdb \
"
