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

# SRCREV_machine_qemuarm ?= "c2507bd62854f90030433307a3d783474332f821"
# SRCREV_machine_qemuarm64 ?= "ddab242999407fadae68e7ee5381b0ec6679d443"
# SRCREV_machine_qemumips ?= "e42198f28a2c3e57ef64a0f5ccfdeed906d1c257"
# SRCREV_machine_qemuppc ?= "ddab242999407fadae68e7ee5381b0ec6679d443"
# SRCREV_machine_qemux86 ?= "ddab242999407fadae68e7ee5381b0ec6679d443"
# SRCREV_machine_qemux86-64 ?= "ddab242999407fadae68e7ee5381b0ec6679d443"
# SRCREV_machine_qemumips64 ?= "2d0080f2350e088bdc6129c376eb9e2654a0448a"
SRCREV_machine ?= "ddab242999407fadae68e7ee5381b0ec6679d443"
SRCREV_meta ?= "b030d96c7b8c391a9e155719d649eb0f87358117"

SRC_URI = "git://git.yoctoproject.org/linux-yocto-4.4.git;name=machine;branch=${KBRANCH}; \
           git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=meta;branch=yocto-4.4;destsuffix=${KMETA}"
SRC_URI += "file://caros-patches.scc"
SRC_URI += "file://caros-features.scc"

LINUX_VERSION ?= "4.4.15"
LINUX_VERSION_EXTENSION = "-caros-${LINUX_KERNEL_TYPE}"

PV = "${LINUX_VERSION}+git${SRCPV}"
PR := "${PR}.2"

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
