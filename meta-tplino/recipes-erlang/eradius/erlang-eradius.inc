DESCRIPTION = "Erlang RADIUS server"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://MIT_LICENSE;md5=7a8830d51731fc79efbb8166db8d2859"

S = "${WORKDIR}/git"
PR = "r0"

inherit tetrapak

python () {
    erlang_def_package("eradius", "eradius-*", "ebin priv", "src include mibs test README.md MIT_LICENSE .arcconfig", d)
}
