SUMMARY = "Exometer Core"
DESCRIPTION = "Exometer Core - Erlang instrumentation package, core services"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=65d26fcc2f35ea6a181ac777e42db1ea"

PR = "r1"

inherit rebar

SRC_URI="git://github.com/Feuerlabs/exometer_core"
SRCREV="5fdd9426713a3c26cae32f644a3120711b1cdb64"

S="${WORKDIR}/git"

DEPENDS += "erlang-lager \
  erlang-parse-trans \
  erlang-meck \
  erlang-folsom \
  erlang-setup \
  erlang-goldrush \
  erlang-edown \
  erlang-bear"

RDEPENDS_${PN} += "erlang-lager \
  erlang-parse-trans \
  erlang-meck \
  erlang-folsom \
  erlang-setup \
  erlang-goldrush \
  erlang-edown \
  erlang-compiler \
  erlang-syntax-tools \
  erlang-bear"

