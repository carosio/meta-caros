HOMEPAGE="https://hex.pm/"
SUMMARY="Hex is a package manager for the Erlang ecosystem."
LICENSE="GPLv2"

PR = "r1"

LIC_FILES_CHKSUM="file://README.md;beginline=29;md5=1675e23b7cf21ff6004f40e5abcfc75a"

SRC_URI = "https://github.com/hexpm/hex/archive/v${PV}.tar.gz"
SRC_URI[md5sum] = "0e7bc3251026295b484171a4104653e9"
SRC_URI[sha256sum] = "d72741129d4b0cdfd2e6591f08abf678e07797f7ca94bb7055f05c99fbae7db7"

DEPENDS += "elixir-native"

do_install() {
        install -d ${D}${libdir}/elixir/lib/
        MIX_ENV=release mix archive.build
        unzip -d ${D}${libdir}/elixir/lib/ hex-${PV}.ez
}

FILES_${PN} += "${libdir}/elixir/lib/hex-0.13.2"

BBCLASSEXTEND = "native"
