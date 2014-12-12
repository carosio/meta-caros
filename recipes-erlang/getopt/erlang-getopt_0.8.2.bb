DESCRIPTION = "Erlang Getopt - Command-line parsing module that uses a syntax similar to that of GNU getopt."
HOMEPAGE = "https://github.com/jcomellas/getopt"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=0689a7b07fec79946ae192573e1450e8"

PR = "r1"

SRC_URI = "https://github.com/jcomellas/getopt/archive/v${PV}.tar.gz"
SRC_URI[md5sum] = "6a4ecb86aa65e8994947317261f54b74"
SRC_URI[sha256sum] = "bfeab0aeb3e515b1c2912874d6e644849561971d8d947ec4adc7c9ff4c394ea8"

S = "${WORKDIR}/getopt-${PV}"

inherit tetrapak

python () {
    erlang_def_package("getopt", "getopt*", "ebin", "Emakefile LICENSE.txt Makefile README.md doc examples rebar rebar.config src test", d)
}
