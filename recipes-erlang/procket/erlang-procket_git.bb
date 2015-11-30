DESCRIPTION = "Erlang interface to low level socket operations"
SECTION = "devel"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://src/bpf.erl;beginline=4;endline=30;md5=1dbe45cb53c97577ab578d9c5211edfc \
                    file://src/procket_mktmp.erl;beginline=4;endline=30;md5=1dbe45cb53c97577ab578d9c5211edfc \
                    file://src/procket.erl;beginline=4;endline=30;md5=1dbe45cb53c97577ab578d9c5211edfc \
                    file://src/procket_ioctl.erl;beginline=4;endline=30;md5=1dbe45cb53c97577ab578d9c5211edfc \
                    file://src/procket_msg.erl;beginline=4;endline=30;md5=1dbe45cb53c97577ab578d9c5211edfc \
                    file://src/packet.erl;beginline=4;endline=30;md5=1dbe45cb53c97577ab578d9c5211edfc \
                    file://include/ioctl.hrl;beginline=4;endline=30;md5=1dbe45cb53c97577ab578d9c5211edfc \
                    file://include/packet.hrl;beginline=4;endline=30;md5=1dbe45cb53c97577ab578d9c5211edfc \
                    file://include/procket_msg.hrl;beginline=4;endline=30;md5=1dbe45cb53c97577ab578d9c5211edfc \
                    file://include/bpf.hrl;beginline=4;endline=30;md5=1dbe45cb53c97577ab578d9c5211edfc \
                    file://include/procket.hrl;beginline=4;endline=30;md5=1dbe45cb53c97577ab578d9c5211edfc \
                    file://c_src/procket_cmd.c;beginline=4;endline=30;md5=019184ce05c8742272a079ddc7c0d6cf \
                    file://c_src/procket.h;beginline=4;endline=30;md5=019184ce05c8742272a079ddc7c0d6cf \
                    file://c_src/ancillary.h;beginline=8;endline=28;md5=af1b50fdd28e5d9fb65e4404418ce623 \
                    file://c_src/fd_recv.c;beginline=8;endline=28;md5=af1b50fdd28e5d9fb65e4404418ce623 \
                    file://c_src/Makefile.ancillary;beginline=7;endline=27;md5=e7c55b1e822972a9b81b1cbe1102836f \
                    file://c_src/fd_send.c;beginline=8;endline=28;md5=af1b50fdd28e5d9fb65e4404418ce623 \
                    file://c_src/procket.c;beginline=4;endline=30;md5=019184ce05c8742272a079ddc7c0d6cf \
                    file://examples/echo.erl;beginline=4;endline=30;md5=1dbe45cb53c97577ab578d9c5211edfc \
                    file://examples/icmp6.erl;beginline=7;endline=33;md5=1dbe45cb53c97577ab578d9c5211edfc \
                    file://examples/icmp.erl;beginline=4;endline=30;md5=1dbe45cb53c97577ab578d9c5211edfc"

SRCREV = "539c6523f35cdb9dbde1911d4aa59dc77e2fd790"
PV = "git${SRCPV}"
PR = "r2"

SRC_URI = "git://git.netdef.org/scm/osr/procket.git;protocol=https \
           file://add-tetrapak.patch"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("procket", "procket-*", "ebin priv", "c_src include src README.md", d)
}
FILES_${PN} += " /usr/local/bin/procket"
FILES_${PN}-dbg += " /usr/local/bin/.debug/procket"

do_install_append() {
    install -d -m 755 ${D}/usr/local/bin
    install ${S}/priv/procket ${D}/usr/local/bin/procket
}
