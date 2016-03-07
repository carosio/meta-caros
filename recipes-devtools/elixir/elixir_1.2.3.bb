HOMEPAGE = "http://elixir-lang.org/"
SUMMARY  = "Elixir - dynamic, functional language"
DESCRIPTION = "Elixir is a dynamic, functional language designed for building scalable and maintainable applications. Elixir leverages the Erlang VM, known for running low-latency, distributed and fault-tolerant systems, while also being successfully used in web development and the embedded software domain."
LICENSE  = "GPLv2"

PR = "r1"

LIC_FILES_CHKSUM = "file://LICENSE;md5=0c48e31d655fb0e9b1f60b931e652f47"

SRC_URI = "https://github.com/elixir-lang/elixir/archive/v${PV}.tar.gz;downloadfilename=elixir-${PV}.tar.gz"
SRC_URI[md5sum] = "660dfacfcbe58542ff4c0bec11bec2b2"
SRC_URI[sha256sum] = "886e4efea0e9bbbb4ba55ea659986fb3460c5b77045410c10144838192214827"

DEPENDS = "erlang"
RDEPENDS_${PN} += "erlang"

FILES_${PN} = "${bindir} ${libdir}"

do_install() {
    oe_runmake install PREFIX=${D}${prefix}
}

BBCLASSEXTEND = "native"
