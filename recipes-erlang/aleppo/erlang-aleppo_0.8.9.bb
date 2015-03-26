SUMMARY = "Aleppo: ALternative Erlang Pre-ProcessOr"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://Emakefile;md5=df5da8c3142fc783e2ddc3695068f02e"

PR = "r1"

SRC_URI = "https://github.com/ChicagoBoss/aleppo/archive/v${PV}.tar.gz;downloadfilename=aleppo-${PV}.tar.gz \
           file://tetrapakize.patch"

SRC_URI[md5sum] = "ba28bba098043b6a758b319dccfdafcd"
SRC_URI[sha256sum] = "4ecd02f892c434083848c9e520f85097800e7736f8d2624cea3c45e0e8e6173d"

S = "${WORKDIR}/aleppo-${PV}"

inherit tetrapak

TETRAPAK_OPTS += "-o build.version ${PV}"

python () {
    erlang_def_package("aleppo", "aleppo*", "ebin priv", "Emakefile src include tetrapak", d)
}
