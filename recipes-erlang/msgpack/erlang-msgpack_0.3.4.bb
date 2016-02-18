SUMMERY = "MessagePack (de)serializer implementation for Erlang"
DESCRIPTION = "MessagePack Erlang"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE-2.0.txt;md5=3b83ef96387f14655fc854ddc3c6bd57"

PR="r1"

SRC_URI = "git://github.com/msgpack/msgpack-erlang.git;protocol=git"
SRCREV = "e84e60dbfff3472f93205415b63e4787bc293d3e"

S ="${WORKDIR}/git"

inherit rebar
