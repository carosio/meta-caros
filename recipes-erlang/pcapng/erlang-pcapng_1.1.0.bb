SUMMARY = "PcapNG encoder/decoder"
SECTION = "devel"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=815ca599c9df247a0c7f619bab123dad"

# temporary location until pcapng is moved to github:
SRC_URI = "git://github.com/travelping/pcapng.git;protocol=https"
SRCREV = "acda9f0b44dd678d41fc658f6f67d78e1574e52d"

PR = "r4"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("pcapng", "pcapng-*", "ebin priv", "src include COPYING test NEWS.md README.adoc", d)
}
