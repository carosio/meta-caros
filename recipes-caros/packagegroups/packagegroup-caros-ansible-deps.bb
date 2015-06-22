DESCRIPTION = "CAROS packagegroup for ansible requirements"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PR = "r2"

# ansible requires some python packages

inherit packagegroup

PACKAGES = "${PN}"
PACKAGE_GROUP_caros_ansible_deps = "${PN}"

RDEPENDS_${PN} = "\
python \
python-compiler \
python-io \
python-json \
python-shell \
python-subprocess \
python-syslog \
python-unixadmin \
"

