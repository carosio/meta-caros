require recipes-kernel/linux/linux-tplino.inc

KBRANCH_DEFAULT = "standard/base"
KBRANCH = "${KBRANCH_DEFAULT}"

SRCREV_machine_qemuarm ?= "${AUTOREV}"
SRCREV_machine_qemumips  ?= "${AUTOREV}"
SRCREV_machine_qemuppc ?= "${AUTOREV}"
SRCREV_machine_qemux86 ?= "${AUTOREV}"
SRCREV_machine_qemux86-64 ?= "${AUTOREV}"
SRCREV_machine ?= "${AUTOREV}"
SRCREV_meta ?= "${AUTOREV}"

SRC_URI = "git://git@git.tpip.net/tplino-linux-3.8;protocol=ssh;bareclone=1;branch=${KBRANCH},${KMETA};name=machine,meta"

LINUX_VERSION ?= "3.8.4"

PR = "${INC_PR}.4"
PV = "${LINUX_VERSION}+${PR}-${SRCPV}"

KMETA = "meta"

COMPATIBLE_MACHINE = "qemuarm|qemux86|qemuppc|qemumips|qemux86-64"

# Functionality flags
KERNEL_REVISION_CHECKING=""
KERNEL_FEATURES_append = " features/netfilter"
KERNEL_FEATURES_append_qemux86=" cfg/sound"
KERNEL_FEATURES_append_qemux86-64=" cfg/sound"
KERNEL_FEATURES_append_qemux86=" cfg/paravirt_kvm"
#KERNEL_FEATURES_append_qemux86-64=" cfg/paravirt_kvm"
KERNEL_FEATURES_append = " ${@bb.utils.contains("TUNE_FEATURES", "mx32", " cfg/x32", "" ,d)}"
