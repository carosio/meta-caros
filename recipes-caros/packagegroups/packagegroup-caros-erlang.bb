DESCRIPTION = "CAROS packagegroup for erlang packages"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PR = "r1"

inherit packagegroup

PACKAGES = "${PN}"
PACKAGE_GROUP_caros_erlang = "${PN}"

RDEPENDS_${PN} = "\
erlang-dike \
erlang-dike-cache \
erlang-ejournald \
erlang-eradius \
erlang-hello \
erlang-lager \
erlang-lager-journald-backend \
"

