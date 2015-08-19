require recipes-connectivity/iproute2/iproute2.inc

SRC_URI[md5sum] = "51c54cc3245eff451154938fbc0f64f5"
SRC_URI[sha256sum] = "a4101e743e0da36b55f63353ff489699ddcd634ceca854a9a272346588f30f30"

SRC_URI = "https://www.kernel.org/pub/linux/utils/net/iproute2/iproute2-4.2.0.tar.xz \
           file://configure-cross.patch \
           file://0001-iproute2-de-bash-scripts.patch \
          "
# CFLAGS are computed in Makefile and reference CCOPTS
#
EXTRA_OEMAKE_append = " CCOPTS='${CFLAGS}'"
