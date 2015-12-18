HOMEPAGE="https://hex.pm/"
SUMMARY="Hex is a package manager for the Erlang ecosystem."
LICENSE="GPLv2"

PR = "r1"

LIC_FILES_CHKSUM="file://README.md;md5=78f4800e20430507c93c84b6d507cbad"

SRC_URI = "https://github.com/hexpm/hex/archive/v${PV}.tar.gz"
SRC_URI[md5sum] = "ec5cfb9b2c8884b1164dc177cfebd9d4"
SRC_URI[sha256sum] = "bf878139a05d9509bc7a7f843b3e672111023202eb010ee91138846da1d1101f"

DEPENDS += "elixir-native"

do_install() {
        install -d ${D}${libdir}/elixir/lib/
        MIX_ENV=release mix archive.build
        unzip -d ${D}${libdir}/elixir/lib/ hex-${PV}.ez
}

FILES_${PN} += "${libdir}/elixir/lib/hex-0.8.3"

BBCLASSEXTEND = "native"
