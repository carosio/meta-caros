DESCRIPTION = "Erlang interface to low level socket operations"
SECTION = "devel"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://examples/echo.erl;beginline=4;endline=30;md5=1dbe45cb53c97577ab578d9c5211edfc \
                    file://examples/sendmsg_recvmsg_echo.erl;beginline=4;endline=30;md5=1dbe45cb53c97577ab578d9c5211edfc \
                    file://examples/icmp.erl;beginline=4;endline=30;md5=1dbe45cb53c97577ab578d9c5211edfc \
                    file://examples/icmp6.erl;beginline=7;endline=33;md5=1dbe45cb53c97577ab578d9c5211edfc \
                    file://c_src/ancillary.h;beginline=8;endline=28;md5=af1b50fdd28e5d9fb65e4404418ce623 \
                    file://c_src/fd_send.c;beginline=8;endline=28;md5=af1b50fdd28e5d9fb65e4404418ce623 \
                    file://c_src/procket_cmd.c;beginline=4;endline=30;md5=019184ce05c8742272a079ddc7c0d6cf \
                    file://c_src/procket.h;beginline=4;endline=30;md5=019184ce05c8742272a079ddc7c0d6cf \
                    file://c_src/fd_recv.c;beginline=8;endline=28;md5=af1b50fdd28e5d9fb65e4404418ce623 \
                    file://c_src/procket.c;beginline=4;endline=30;md5=019184ce05c8742272a079ddc7c0d6cf \
                    file://include/packet.hrl;beginline=4;endline=30;md5=1dbe45cb53c97577ab578d9c5211edfc \
                    file://include/ioctl.hrl;beginline=4;endline=30;md5=1dbe45cb53c97577ab578d9c5211edfc \
                    file://include/bpf.hrl;beginline=4;endline=30;md5=1dbe45cb53c97577ab578d9c5211edfc \
                    file://include/procket.hrl;beginline=4;endline=30;md5=1dbe45cb53c97577ab578d9c5211edfc \
                    file://src/packet.erl;beginline=4;endline=30;md5=1dbe45cb53c97577ab578d9c5211edfc \
                    file://src/procket.erl;beginline=4;endline=30;md5=1dbe45cb53c97577ab578d9c5211edfc \
                    file://src/procket_mktmp.erl;beginline=4;endline=30;md5=1dbe45cb53c97577ab578d9c5211edfc \
                    file://src/procket_ioctl.erl;beginline=4;endline=30;md5=1dbe45cb53c97577ab578d9c5211edfc \
                    file://src/bpf.erl;beginline=4;endline=30;md5=1dbe45cb53c97577ab578d9c5211edfc"

SRCREV = "28d18658b3e9fe41aaeab3852dd0f5e108ab4838"

SRC_URI = "git://github.com/msantos/procket.git;protocol=https \
           file://add-tetrapak.patch"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("procket", "procket-*", "ebin priv", "c_src include src README.md", d)
}
FILES_${PN} += " /usr/local/bin/procket"
FILES_${PN}-dbg += " /usr/local/bin/.debug/procket"

do_install_append() {
    rm -rf ${D}/usr/lib/erlang/lib/procket-*/rebar
    chmod 4755 ${D}/usr/lib/erlang/lib/procket-*/priv/procket
}
