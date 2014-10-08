SECTION = "libs"
LICENSE = "Artistic-1.0 | GPL-1.0"
LIC_FILES_CHKSUM = "file://README;beginline=31;endline=37;md5=234655246e20d3f949d57a0ef2f685aa"
PR = "r0"

SRC_URI = "http://www.cpan.org/modules/by-module/Text/Text-CSV-${PV}.tar.gz"

SRC_URI[md5sum] = "f476205648694a64684be2ab29e44e19"
SRC_URI[sha256sum] = "b49fea66d75a1419a76b0b2b30144d97e1f69728928c06ed08405874fd8ff9af"

S = "${WORKDIR}/Text-CSV-${PV}"

EXTRA_PERLFLAGS = "-I ${STAGING_LIBDIR_NATIVE}/perl-native/perl/${@get_perl_version(d)}"

inherit cpan

BBCLASSEXTEND = "native"
