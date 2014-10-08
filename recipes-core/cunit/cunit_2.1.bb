DECRIPTION = "Cunit A unit testing framework for C"
SECTION = "development tools"
LICENSE = "GPL"
LIC_FILES_CHKSUM = "file://COPYING;md5=7734aa853b85d6f935466f081490ddbb"

PR = "r1"

SRC_URI = "http://sourceforge.net/projects/cunit/files/CUnit/2.1-2/CUnit-2.1-2-src.tar.bz2"
SRC_URI[md5sum] = "31c62bd7a65007737ba28b7aafc44d3a"

DEPENDS = "gcc"
RDEPENDS_${PN} = "gcc"

S = "${WORKDIR}/CUnit-2.1-2"

FILES_${PN} += " /usr/doc \
  /usr/share/CUnit \
  /usr/share/CUnit/Memory-Dump.xsl \
  /usr/share/CUnit/CUnit-Run.xsl \
  /usr/share/CUnit/CUnit-Run.dtd \
  /usr/share/CUnit/Memory-Dump.dtd \
  /usr/share/CUnit/CUnit-List.dtd \
  /usr/share/CUnit/CUnit-List.xsl \
  /usr/doc/CUnit \
  /usr/doc/CUnit/introduction.html \
  /usr/doc/CUnit/writing_tests.html \
  /usr/doc/CUnit/running_tests.html \
  /usr/doc/CUnit/fdl.html \
  /usr/doc/CUnit/test_registry.html \
  /usr/doc/CUnit/CUnit_doc.css \
  /usr/doc/CUnit/managing_tests.html \
  /usr/doc/CUnit/error_handling.html \
  /usr/doc/CUnit/index.html \
  /usr/doc/CUnit/headers \
  /usr/doc/CUnit/headers/CUError.h \
  /usr/doc/CUnit/headers/MyMem.h \
  /usr/doc/CUnit/headers/TestRun.h \
  /usr/doc/CUnit/headers/Console.h \
  /usr/doc/CUnit/headers/Win.h \
  /usr/doc/CUnit/headers/TestDB.h \
  /usr/doc/CUnit/headers/Basic.h \
  /usr/doc/CUnit/headers/Util.h \
  /usr/doc/CUnit/headers/Automated.h \
  /usr/doc/CUnit/headers/CUnit_intl.h \
  /usr/doc/CUnit/headers/CUCurses.h \
  /usr/doc/CUnit/headers/CUnit.h "

inherit autotools
