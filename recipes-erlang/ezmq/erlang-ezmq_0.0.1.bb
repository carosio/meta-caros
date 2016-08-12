SUMMARY = "ezmq"
DISCRIPTION = "ezmq implements the ØMQ protocol in 100% pure Erlang."
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"

PR = "r1"

inherit rebar

SRC_URI="git://github.com/RoadRunnr/ezmq;branch=fix-socket-crash"
SRCREV = "a77378f0cb716b5293be6f69bdc99ed137a509c4"

S="${WORKDIR}/git"

DEPENDS += "erlang-lager \
  erlang-gen-listener-tcp "


do_compile_prepend() {
  #remove debug erlang opts
  cat rebar.config |tr -s '\n "' |tr -s '\n' '#'|sed -e 's/\(.*\){erl_opts, \[\(.*\)debug_info, {.*}\(.*\)\]}\.#\({.*\)/\1{erl_opts,\[\2\3\]}.#\4/g' | tr -s '#' '\n' > tmp_rebar.config
  mv tmp_rebar.config rebar.config
}
