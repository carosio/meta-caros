HOMEPAGE = "http://elixir-lang.org/"
SUMMARY  = "Elixir is a dynamic, functional language designed for building scalable and maintainable applications"
DESCRIPTION = "Elixir leverages the Erlang VM, known for running low-latency, distributed and fault-tolerant systems, while also being successfully used in web development and the embedded software domain."
LICENSE  = "GPLv2"

PR = "r1"

LIC_FILES_CHKSUM = "file://LICENSE;md5=0c48e31d655fb0e9b1f60b931e652f47"

SRC_URI = "https://github.com/elixir-lang/elixir/archive/v${PV}.tar.gz"

SRC_URI[md5sum] = "7e1e88e012d93ea143188a7b17c52917"
SRC_URI[sha256sum] = "79341fde3b01217aa252b8996d1b1b27cd1006cb89fe43446d5241e3dcb84bad"

DEPENDS = "erlang"
RDEPENDS_${PN} += "erlang"

S = "${WORKDIR}/elixir-${PV}"

do_install() {
   install -d ${D}${bindir}
   install -d ${D}${libdir}

   install -m 755 ${S}/bin/elixir ${D}${bindir}
   install -m 755 ${S}/bin/elixirc ${D}${bindir}
   install -m 755 ${S}/bin/iex ${D}${bindir}
   install -m 755 ${S}/bin/mix ${D}${bindir}

   for dir in `ls ${S}/lib`; do
       install -d ${D}${libdir}/$dir
       cp -fr ${S}/lib/$dir/ebin ${D}${libdir}/$dir/
   done
}

FILES_${PN} = "${bindir} ${libdir}"

BBCLASSEXTEND = "native"
