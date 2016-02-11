HOMEPAGE = "http://elixir-lang.org/"
SUMMARY  = "Elixir - dynamic, functional language"
DESCRIPTION = "Elixir is a dynamic, functional language designed for building scalable and maintainable applications. Elixir leverages the Erlang VM, known for running low-latency, distributed and fault-tolerant systems, while also being successfully used in web development and the embedded software domain."
LICENSE  = "GPLv2"

PR = "r1"

LIC_FILES_CHKSUM = "file://LICENSE;md5=0c48e31d655fb0e9b1f60b931e652f47"

SRC_URI = "https://github.com/elixir-lang/elixir/archive/v${PV}.tar.gz;downloadfilename=elixir-${PV}.tar.gz"
SRC_URI[md5sum] = "3c5cb26e9ee144002ecb22cb2778bd53"
SRC_URI[sha256sum] = "09a51fe2680070e67c0b3ef1aeb6c409f383fc69da155020466bc54203d58056"

DEPENDS = "erlang"
RDEPENDS_${PN} += "erlang"

FILES_${PN} = "${bindir} ${libdir}"

do_install() {
    oe_runmake install PREFIX=${D}${prefix}
}

BBCLASSEXTEND = "native"
