DESCRIPTION = "IS-IS with autoconfiguration"
SECTION = "net"

# Package is actually GPL-2.0, however as there are no files
# in this package, I don't know how to build a valid
# LIC_FILES_CHKSUM which is required for any license != CLOSED
LICENSE = "CLOSED"

RDEPENDS_${PN} += " erlang-isis quagga"

NODENAME = "autoisis"
NODEUSER = "autoisis"
NODEGROUP = "autoisis"
NODEUSERCOMMENT = "AutoISIS"
PR = "r3"

inherit enitrelease
