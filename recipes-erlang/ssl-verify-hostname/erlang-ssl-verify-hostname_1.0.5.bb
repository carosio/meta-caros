SUMMARY = "ssl_verify_hostname"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8985f0fc28c309c13b43436372cb11a2"

PR = "r1"

SRC_URI = "https://s3.amazonaws.com/s3.hex.pm/tarballs/ssl_verify_hostname-${PV}.tar;downloadfilename=ssl_verify_hostname-${PV}.tar"
SRC_URI[md5sum] = "ba549a23145c6bdb15ba2f84b6187ad8"
SRC_URI[sha256sum] = "f2cb11e6144e10ab39d1e14bf9fb2437b690979c70bf5428e9dc4bfaf1dfeabf"

S = "${WORKDIR}/ssl_verify_hostname-${PV}"

RDEPENDS_${PN} += "erlang-ssl"

inherit rebar
