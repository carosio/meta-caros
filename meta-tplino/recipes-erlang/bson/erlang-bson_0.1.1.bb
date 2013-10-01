DESCRIPTION = "BSON are JSON-like objects with a standard binary serialization. See bsonspec.org."
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=ed4f50d32302a100094afb36ee379bc1"

SRCREV = "54942e7d84cf083127bf36a98981639654ebb60a"
PR = "r1"

SRC_URI = "git://git@git.tpip.net/bson;protocol=ssh"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("bson", "bson*", "ebin priv", ".gitignore src include", d)
}
