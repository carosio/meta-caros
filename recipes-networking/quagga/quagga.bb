SRCREV = "8bc284f4253176c4711ec2800a61acd09e685ef8"
PV = "0.99.22+git${SRCPV}"
PR = "r2"

require quagga.inc

SRC_URI += "file://babel-close-the-stdout-stderr-as-in-other-daemons.patch \
"

QUAGGASUBDIR = ""
