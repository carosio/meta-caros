FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}_git:"

require openvswitch.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=5973c953e3c8a767cf0808ff8a0bac1b"

SRC_URI_prepend = "git://github.com/openvswitch/ovs.git;protocol=git"
SRC_URI += "file://fix_missing_dist_file.patch \
	    file://remove-depmod.patch"

SRCREV="ebdabd05e9465dd6e50adedb4a0bcb5678e563de"

S = "${WORKDIR}/git"

PR = "${INC_PR}.1"

CFLAGS += "-std=gnu11"

do_fix_reproducible_builds() {
    SED_DATE=`date +"%b %_d %Y"`
    SED_TIME=`date +"%T"`
    sed -i -e"s/__DATE__/\"${SED_DATE}\"/g" \
           -e"s/__TIME__/\"${SED_TIME}\"/g" \
        ${S}/datapath/datapath.c ${S}/include/openvswitch/util.h ${S}/lib/util.c
}

do_patch_append() {
    bb.build.exec_func('do_fix_reproducible_builds', d)
}

do_install_append() {
    rm -f ${D}/usr/bin/ovs-docker ${D}/usr/bin/ovs-testcontroller
}
