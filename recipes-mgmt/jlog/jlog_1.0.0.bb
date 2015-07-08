SUMMARY = "journald logging tool"
SECTION = "logging"
DEPENDS += "systemd"
LICENSE = "MPL-2.0"
PR = "r1"

LIC_FILES_CHKSUM = "file://LICENCE.MPL2;md5=9741c346eef56131163e13b9db1241b3"

SRC_URI = "https://github.com/carosio/jlog/archive/v${PV}.tar.gz;downloadfilename=${PN}-${PV}.tar.gz"
SRC_URI[md5sum] = "240069c8b366d857a64e334426a24860"
SRC_URI[sha256sum] = "b3bec35e504d15975626c35505267d0bc479e90eb4cbe6607622f572ea49ffb2"

S = "${WORKDIR}/${PN}-${PV}"

do_compile() {
    cd ${S}
    oe_runmake
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/${PN} ${D}${bindir}
}

FILES_${PN} = "${bindir}/${PN}"
