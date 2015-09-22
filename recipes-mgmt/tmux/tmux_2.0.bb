SUMMARY = "Terminal multiplexer"
HOMEPAGE = "https://tmux.github.io/"
SECTION = "console/utils"

LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://tmux.c;beginline=3;endline=17;md5=8685b4455330a940fab1ff451aa941a0"

DEPENDS = "ncurses libevent"

SRC_URI = "https://github.com/${BPN}/${BPN}/releases/download/${PV}/${BP}.tar.gz"
SRC_URI[md5sum] = "9fb6b443392c3978da5d599f1e814eaa"
SRC_URI[sha256sum] = "795f4b4446b0ea968b9201c25e8c1ef8a6ade710ebca4657dd879c35916ad362"

inherit autotools pkgconfig

do_configure_prepend () {
    sed -i -e 's:-I/usr/local/include::' ${S}/Makefile.am || bb_fatal "sed failed"
}

# build in-tree
B = "${S}"
