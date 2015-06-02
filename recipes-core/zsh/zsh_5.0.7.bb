require zsh.inc
DEPENDS += "libcap libpcre gdbm groff-native"

PR = "r1"

SRC_URI = "${SOURCEFORGE_MIRROR}/${BPN}/${BP}.tar.gz"

SRC_URI[md5sum] = "76726ff50309e628de670476e0508b3a"
SRC_URI[sha256sum] = "43f0a4c179ef79bb8c9153575685f7f45f28a3615c8cf96345f503d5b9e7b919"

