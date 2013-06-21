DESCRIPTION = "Erlang CAPWAP protocol implementation"
SECTION = "devel"
LICENSE = "CLOSED"

S = "${WORKDIR}/git"

ERLVSN = '16'

inherit tetrapak

python () {
    erlang_def_package("capwap", "capwap-*", "ebin priv", "src include test", d)
}
