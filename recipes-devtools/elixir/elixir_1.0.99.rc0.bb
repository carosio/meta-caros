HOMEPAGE = "http://elixir-lang.org/"
SUMMARY  = "Elixir is a dynamic, functional language designed for building scalable and maintainable applications"
DESCRIPTION = "Elixir leverages the Erlang VM, known for running low-latency, distributed and fault-tolerant systems, while also being successfully used in web development and the embedded software domain."
LICENSE  = "GPLv2"

PR = "r2"

LIC_FILES_CHKSUM = "file://LICENSE;md5=0c48e31d655fb0e9b1f60b931e652f47"

SRC_URI = "git://github.com/elixir-lang/elixir;protocol=git;name=elixir;destsuffix=git-elixir"
SRCREV_elixir = "0e3c06b03149022b980e69872003d401c4788fea"

PV_hex = "0.8.3"
SRC_URI += " https://github.com/hexpm/hex/archive/v${PV_hex}.tar.gz;name=hex"

SRC_URI[hex.md5sum] = "ec5cfb9b2c8884b1164dc177cfebd9d4"
SRC_URI[hex.sha256sum] = "bf878139a05d9509bc7a7f843b3e672111023202eb010ee91138846da1d1101f"

DEPENDS = "erlang"
RDEPENDS_${PN} += "erlang"

S = "${WORKDIR}/git-elixir"

do_install() {
   oe_runmake install PREFIX=${D}${prefix}

   export PATH="${D}${bindir}:$PATH"
   cd "${WORKDIR}/hex-${PV_hex}"
   MIX_ENV=release mix archive.build
   unzip -d ${D}${libdir}/elixir/lib/ hex-${PV_hex}.ez
}

FILES_${PN} = "${bindir} ${libdir}"

BBCLASSEXTEND = "native"
