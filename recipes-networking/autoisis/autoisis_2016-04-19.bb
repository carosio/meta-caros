DESCRIPTION = "IS-IS with autoconfiguration"
SECTION = "net"

LICENSE = "CLOSED"

RDEPENDS_${PN} += " erlang-isis quagga"

NODENAME = "autoisis"
NODEUSER = "autoisis"
NODEGROUP = "autoisis"
NODEUSERCOMMENT = "AutoISIS"

inherit enitrelease
