FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-${PV}:"

KBRANCH ?= "standard/base"

require recipes-kernel/linux/linux-yocto.inc

# board specific branches
KBRANCH_qemuarm  ?= "standard/arm-versatile-926ejs"
KBRANCH_qemuarm64 ?= "standard/qemuarm64"
KBRANCH_qemumips ?= "standard/mti-malta32"
KBRANCH_qemuppc  ?= "standard/qemuppc"
KBRANCH_qemux86  ?= "standard/base"
KBRANCH_qemux86-64 ?= "standard/base"
KBRANCH_qemumips64 ?= "standard/mti-malta64"

SRCREV_machine_qemuarm64 ?= "2dadc3524fcbce0c46f5db65b7c20c673fc60503"

# SRCREV_machine_qemuarm ?= "bc11ecf659e30c8687604e086450ae1ff90c0169"
# SRCREV_machine_qemumips ?= "181e2c553ffaae19d08a6730e98d288c7e337d39"
# SRCREV_machine_qemuppc ?= "dbe692d91c8e55d1430f2c45fd578c8e4e71e482"
# SRCREV_machine_qemux86 ?= "dbe692d91c8e55d1430f2c45fd578c8e4e71e482"
# SRCREV_machine_qemux86-64 ?= "dbe692d91c8e55d1430f2c45fd578c8e4e71e482"
# SRCREV_machine_qemumips64 ?= "2684711a16ed21f114d834742f860cee532f7438"
SRCREV_machine ?= "2dadc3524fcbce0c46f5db65b7c20c673fc60503"
SRCREV_meta ?= "ad9d3f01300ba350563e17db00b2518302e172f6"

SRC_URI = "git://git.yoctoproject.org/linux-yocto-4.4.git;name=machine;branch=${KBRANCH}; \
           git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=yocto-4.4;destsuffix=${KMETA}"
SRC_URI += "file://caros-patches.scc"

LINUX_VERSION ?= "4.4"
LINUX_VERSION_EXTENSION = "-caros-${LINUX_KERNEL_TYPE}"

PV = "${LINUX_VERSION}+git${SRCPV}"

KMETA = "kernel-meta"
KCONF_BSP_AUDIT_LEVEL = "2"

COMPATIBLE_MACHINE = "qemuarm|qemuarm64|qemux86|qemuppc|qemumips|qemumips64|qemux86-64"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc"
KERNEL_FEATURES_append = " ${KERNEL_EXTRA_FEATURES}"
KERNEL_FEATURES_append = " ${@bb.utils.contains("TUNE_FEATURES", "mx32", " cfg/x32.scc", "" ,d)}"


# Install a relative symlink instead of an absolute one to support btrfs layout in grub
pkg_postinst_kernel-image () {
	update-alternatives --install /${KERNEL_IMAGEDEST}/${KERNEL_IMAGETYPE} ${KERNEL_IMAGETYPE} ${KERNEL_IMAGETYPE}-${KERNEL_VERSION} ${KERNEL_PRIORITY} || true
}
