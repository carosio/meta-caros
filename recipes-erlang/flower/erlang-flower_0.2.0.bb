require erlang-flower.inc

SRC_URI = "git://git@git.tpip.net/flower.git;protocol=ssh;branch=of12_exp"
SRCREV = "${AUTOREV}"

PV-orig := "${PV}" 
PV = "${PV-orig}-${PR}-${SRCPV}" 
PR = "r6"
