SUMMARY = "Recipe for erlang-erlando"
SECTION = "net"
LICENSE = "MPL-1.0"
LIC_FILES_CHKSUM = "file://README.md;beginline=617;md5=b5d78b4446f5ee806a90c7416aada096"

PR = "r3"

# Note: ChicagoBoss forked and uses our version for R17.0 compatibility,
#       so keeping out repo in here is ok, last checked: 26-06-2014 (as)

SRCREV = "23d678c9763d130d345d6363bca66940eca31c56"

SRC_URI = "https://github.com/travelping/erlando/archive/${SRCREV}.tar.gz;downloadfilename=erlando-${SRCREV}.tar.gz \
           file://tetrapakize.patch;apply=yes"

SRC_URI[md5sum] = "7c81456a4856a85068cf50a1f511ce4e"
SRC_URI[sha256sum] = "9f23092d150f8513d37508b06cf2364f2b5fbb5a07b75ac7706e5a055b413df0"

DEPENDS = "erlang-pmod-transform"

S = "${WORKDIR}/erlando-${SRCREV}"

inherit tetrapak

CLEANBROKEN = "1"

TETRAPAK_ERL_LIBS = "${S}"
TETRAPAK_OPTS += "-o build.version 2.8.7~23d678c976"

python () {
    erlang_def_package("erlando", "erlando*", "ebin", "README.md src include test", d)
}
