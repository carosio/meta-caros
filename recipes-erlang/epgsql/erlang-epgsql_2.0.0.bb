DESCRIPTION = "PostgreSQL Client"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9f05431c7ad1940db8f544ddb9cf8b80"

PR = "r1"

SRC_URI = "https://github.com/epgsql/epgsql/archive/${PV}.tar.gz;downloadfilename=epgsql-${PV}.tar.gz \
           file://tetrapakize.patch"

SRC_URI[md5sum] = "7d383a0ecd909dd346c1900daf9387fe"
SRC_URI[sha256sum] = "ee6b3b2b701087502ef7d752a62327d9429c3e16b1a21fc301aedb5ad9ad639f"

S = "${WORKDIR}/epgsql-${PV}"

inherit tetrapak

TETRAPAK_OPTS += "-o build.version ${PV}"

python () {
    erlang_def_package("epgsql", "epgsql*", "ebin priv", "CHANGES TODO src include test_data test", d)
}
