HOMEPAGE = "http://elixir-lang.org/"
SUMMARY  = "Elixir is a dynamic, functional language designed for building scalable and maintainable applications"
DESCRIPTION = "Elixir leverages the Erlang VM, known for running low-latency, distributed and fault-tolerant systems, while also being successfully used in web development and the embedded software domain."
LICENSE  = "GPLv2"

PR = "r2"

LIC_FILES_CHKSUM = "file://LICENSE;md5=0c48e31d655fb0e9b1f60b931e652f47"

SRC_URI = "https://github.com/elixir-lang/elixir/archive/v${PV}.tar.gz;downloadfilename=elixir-${PV}.tar.gz"
SRC_URI[md5sum] = "e952220c61fae06bdc59d7d0068d9f6d"
SRC_URI[sha256sum] = "3b7d6e4fdbcc82d19fa76f4e384f8a87535abcd00ef04528dc6b6706f32a106a"

DEPENDS = "erlang"
RDEPENDS_${PN} += "erlang"

FILES_${PN} = "${bindir} ${libdir}"

do_install() {
    oe_runmake install PREFIX=${D}${prefix}
}

BBCLASSEXTEND = "native"
