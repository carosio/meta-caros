SUMMARY = "glibc GHOST vulnerability check"
HOMEPAGE = "http://www.openwall.com/lists/oss-security/2015/01/27/9"
SECTION = "security"
LICENSE = "CLOSED"
 
SRC_URI = "file://GHOST.c"

PR = "r1"
 
S = "${WORKDIR}"
 
do_compile() {
    ${CC} ${CFLAGS} ${LDFLAGS} GHOST.c -o glibc-ghost-check
}
 
do_install() {
    install -d ${D}${bindir}
    install -m 0755 glibc-ghost-check ${D}${bindir}
}
