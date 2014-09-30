DESCRIPTION = "Medici Tokyo Tyrant interface"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README;md5=8614e07bbe19bc8653206be7fd3dd276"

SRCREV="6bfd658001ee9cef14497f4f6c64174940552cb3"
PR = "r1"

SRC_URI = "git://git@git.tpip.net/medici.git;protocol=ssh;branch=rebarify"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("medici", "medici*", "ebin priv", "README TODO rebar src include", d)
}

