SUMMARY = "Erlang SDK tasks for TPLINO"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=3f40d7994397109285ec7b81fdeb3b58 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PR = "r0"

ALLOW_EMPTY_${PN} = "1"

PACKAGES = "\
    task-core-erlang-sdk \
    task-core-erlang-sdk-dbg \
    task-core-erlang-sdk-dev \
    "
 
ALLOW_EMPTY_${PN} = "1"

RDEPENDS_${PN}-sdk = "\
    erlang \
    erlang-appmon \
    erlang-asn1 \
    erlang-common-test \
    erlang-corba \
    erlang-crypto \
    erlang-debugger \
    erlang-dialyzer \
    erlang-diameter \
    erlang-edoc \
    erlang-eldap \
    erlang-erl-docgen \
    erlang-et \
    erlang-eunit \
    erlang-gs \
    erlang-ic \
    erlang-ic-java \
    erlang-inets \
    erlang-inviso \
    erlang-manpages \
    erlang-megaco \
    erlang-mnesia \
    erlang-observer \
    erlang-odbc \
    erlang-os-mon \
    erlang-parsetools \
    erlang-percept \
    erlang-pman \
    erlang-public-key \
    erlang-reltool \
    erlang-runtime-tools \
    erlang-snmp \
    erlang-ssh \
    erlang-ssl \
    erlang-syntax-tools \
    erlang-test-server \
    erlang-toolbar \
    erlang-tools \
    erlang-tv \
    erlang-typer \
    erlang-webtool \
    erlang-wx \
    erlang-xmerl \
    erlang-jinterface"
