require recipes-kernel/linux/linux-tplino.inc

KBRANCH_DEFAULT = "standard/base"
KBRANCH = "${KBRANCH_DEFAULT}"

SRCREV_machine_qemuarm ?= "AUTOINC"
SRCREV_machine_qemumips  ?= "AUTOINC"
SRCREV_machine_qemuppc ?= "AUTOINC"
SRCREV_machine_qemux86 ?= "AUTOINC"
SRCREV_machine_qemux86-64 ?= "AUTOINC"
SRCREV_machine ?= "AUTOINC"
SRCREV_meta ?= "AUTOINC"

SRC_URI = "git://git@git.tpip.net/tplino-linux-3.8;protocol=ssh;bareclone=1;branch=${KBRANCH},${KMETA};name=machine,meta"

LINUX_VERSION ?= "3.8"

PR = "${INC_PR}.3"
PV = "${LINUX_VERSION}+git${SRCPV}"

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
