HOMEPAGE="https://hex.pm/"
SUMMARY="Hex is a package manager for the Erlang ecosystem."
LICENSE="GPLv2"

PR = "r1"

LIC_FILES_CHKSUM="file://README.md;beginline=29;md5=1675e23b7cf21ff6004f40e5abcfc75a"

SRC_URI = "https://github.com/hexpm/hex/archive/v${PV}.tar.gz"
SRC_URI[md5sum] = "87a383c7f06689879519c8af0dcde594"
SRC_URI[sha256sum] = "2274028279013831b90f04afbf478797ebf55f805c01624402199fbd4c12397c"

DEPENDS += "elixir-native"

do_install() {
        install -d ${D}${libdir}/elixir/lib/
        MIX_ENV=release mix archive.build
        unzip -d ${D}${libdir}/elixir/lib/ hex-${PV}.ez
}

FILES_${PN} += "${libdir}/elixir/lib/hex-0.10.4"

BBCLASSEXTEND = "native"
