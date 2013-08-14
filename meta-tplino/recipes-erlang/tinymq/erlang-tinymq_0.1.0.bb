DESCRIPTION = "TinyMQ: a diminutive message queue"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://Emakefile;md5=3c1a641e9564c70737d27e3079ec85f1"

SRCREV = "269b5c5aa24584414772baf98312488c2ec05704"
PR = "r2"
PV-orig := "${PV}" 
PV = "${PV-orig}-${PR}-${SRCPV}" 

SRC_URI = "git://git@git.tpip.net/tinymq;protocol=ssh"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("tinymq", "tinymq*", "ebin priv", "src include tests", d)
}
