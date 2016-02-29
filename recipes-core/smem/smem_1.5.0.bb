SUMMARY = "smem - a tool for meaningful memory reporting"
SECTION = "devel"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://smemcap.c;beginline=1;endline=9;md5=e4da71382ef4ef16f3c9544793f3198f"

PR = "r1"

SRC_URI = "https://selenic.com/repo/smem/archive/1.5.zip;downloadfilename=smem-1.5.zip"
SRC_URI[md5sum] = "87cd4ad5f66a928bc3a4150493c6b490"
SRC_URI[sha256sum] = "9fee94e3f8665033e993d06673973d6283a3f903ad1dac5d8385a1fdb8f546a2"

S = "${WORKDIR}/smem-1.5"

do_compile() {
        oe_runmake smemcap
}

do_install() {
        install -d ${D}${sbindir}
        install -m 755 ${S}/smem ${D}${sbindir}/smem
        install -m 755 ${S}/smemcap ${D}${sbindir}/smemcap
}

PACKAGES =+ "${PN}-smemcap"

FILES_${PN} += "${sbindir}/smem"
FILES_${PN}-smemcap += "${sbindir}/smemcap"

RDEPENDS_${PN} += "python-re \
                python-compression \
                python-textutils \
                python-shell \
                smem-smemcap"
