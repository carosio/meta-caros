DESCRIPTION = "FreeWTP"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS += "kmod-native virtual/kernel libconfig libnl wolfssl"

RREPLACES_${PN} = "smartcapwap"
RCONFLICTS_${PN} = "smartcapwap"

RREPLACES_kernel-module-wtp = "kernel-module-smartcapwap"
RCONFLICTS_kernel-module-wtp = "kernel-module-smartcapwap"

PR = "r1"

SRC_URI = "git://github.com/travelping/freewtp;protocol=https"
SRCREV = "38692715091372f932bced098b8ea8ffe2b8522b"

S="${WORKDIR}/git"

PACKAGES_DYNAMIC += "^kernel-module-.*"

FILES_${PN} = "${bindir}/wtp"

inherit module-base kernel-module-split autotools pkgconfig

do_configure_prepend() {
    sed -i -e"s|-Wall -Werror -g -O0|-Wall -Werror -g|g" ${S}/configure.ac
}

do_compile_append() {
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
    oe_runmake KERNEL_PATH=${STAGING_KERNEL_DIR}   \
               KERNEL_VERSION=${KERNEL_VERSION}    \
               CC="${KERNEL_CC}" LD="${KERNEL_LD}" \
               AR="${KERNEL_AR}" \
               O=${STAGING_KERNEL_BUILDDIR} \
               M="${S}/kmod" \
               -C ${STAGING_KERNEL_DIR} modules
}

do_install_append() {
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
    oe_runmake DEPMOD=echo INSTALL_MOD_PATH="${D}" \
               CC="${KERNEL_CC}" LD="${KERNEL_LD}" \
               O=${STAGING_KERNEL_BUILDDIR} \
               M="${S}/kmod" \
               -C ${STAGING_KERNEL_DIR} modules_install
}

addtask make_scripts after do_patch before do_compile
do_make_scripts[lockfiles] = "${TMPDIR}/kernel-scripts.lock"
do_make_scripts[deptask] = "do_populate_sysroot"
