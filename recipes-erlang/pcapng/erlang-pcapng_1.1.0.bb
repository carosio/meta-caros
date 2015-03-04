DESCRIPTION = "PcapNG encoder/decoder"
SECTION = "devel"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=815ca599c9df247a0c7f619bab123dad"

# temporary location until pcapng is moved to github:
SRC_URI = "git://git@gitlab.tpip.net/thintze/pcapng.git;protocol=ssh"
SRCREV = "10d90b8e03fa9af3955fa6bce8c83f32a014191c"

PR = "r3"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("pcapng", "pcapng-*", "ebin priv", "src include COPYING test NEWS.md README.adoc", d)
}