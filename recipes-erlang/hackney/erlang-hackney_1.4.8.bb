SUMMARY = "hackney"
DESCRIPTION = "hackney - HTTP client library in Erlang"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9f9455f55b479b71d3db4b727f3eae5f"

PR = "r1"

SRC_URI = "https://s3.amazonaws.com/s3.hex.pm/tarballs/hackney-${PV}.tar;downloadfilename=hackney-${PV}.tar \
           file://rebar-config.patch"
SRC_URI[md5sum] = "01e70efc97e34e6fe27abe346d6f9f3f"
SRC_URI[sha256sum] = "7c212741163cec1cf38ad845bd71efafcbf81365cf754c978b354c6375dc4da2"

S = "${WORKDIR}/hackney-${PV}"

inherit rebar

DEPENDS +=  "erlang-idna \
             erlang-mimerl \
             erlang-certifi \
             erlang-ssl-verify-hostname"
RDEPENDS_${PN} +=  "erlang-idna \
             erlang-mimerl \
             erlang-certifi \
             erlang-ssl-verify-hostname"

do_configure_prepend() {
        rm -f ${S}/rebar.config.script
}
