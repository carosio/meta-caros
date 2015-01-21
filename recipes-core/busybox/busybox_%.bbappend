# The entries here will override any entries in the base busybox recipe
#
# More details can be found in the Kernel Dev Manual
# http://www.yoctoproject.org/docs/current/kernel-dev/kernel-dev.html#changing-the-configuration
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += "file://no_ifup.cfg \
            file://large_history.cfg \
            file://netstat.cfg \
            file://reverse_search.cfg \
            file://unicode.cfg"
