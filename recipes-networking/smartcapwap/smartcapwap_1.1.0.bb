DESCRIPTION = "SmartCAPWAP"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${S}/LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

DEPENDS += "kmod-native virtual/kernel libconfig libnl wolfssl"

PR = "r1"

SRC_URI = "git://github.com/travelping/smartcapwap;protocol=https"
SRCREV = "1d95a62061d473de299a42f53fc600ad232dc025"

S="${WORKDIR}/git"

PACKAGES_DYNAMIC += "^kernel-module-.*"

FILES_${PN} = "${bindir}/wtp"

inherit module-base kernel-module-split autotools pkgconfig

EXTRA_OECONF = "--disable-ac"

PARALLEL_MAKE = ""

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
               M="${S}/src/wtp/kmod" \
               -C ${STAGING_KERNEL_DIR} modules
}

do_install_append() {
    unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
    oe_runmake DEPMOD=echo INSTALL_MOD_PATH="${D}" \
               CC="${KERNEL_CC}" LD="${KERNEL_LD}" \
               O=${STAGING_KERNEL_BUILDDIR} \
               M="${S}/src/wtp/kmod" \
               -C ${STAGING_KERNEL_DIR} modules_install
}

addtask make_scripts after do_patch before do_compile
do_make_scripts[lockfiles] = "${TMPDIR}/kernel-scripts.lock"
do_make_scripts[deptask] = "do_populate_sysroot"
