HOMEPAGE="https://hex.pm/"
SUMMARY="Hex is a package manager for the Erlang ecosystem."
LICENSE="GPLv2"

PR = "r1"

LIC_FILES_CHKSUM="file://README.md;md5=8edcd66591f5e88907f8af80a1a14745"

SRC_URI = "https://github.com/hexpm/hex/archive/v${PV}.tar.gz"
SRC_URI[md5sum] = "f45fb511a801fc30ebf472784542173f"
SRC_URI[sha256sum] = "8b5f4faaaed6746908d5a0b596d2ca19c586b09845a1ce9906c5cfc85fa48377"

DEPENDS += "elixir-native"

do_install() {
        install -d ${D}${libdir}/elixir/lib/
        MIX_ENV=release mix archive.build
        unzip -d ${D}${libdir}/elixir/lib/ hex-${PV}.ez
}

FILES_${PN} += "${libdir}/elixir/lib/hex-0.16.0"

BBCLASSEXTEND = "native"
