SUMMARY = "Vi IMproved - enhanced vi editor"
SECTION = "console/utils"
DEPENDS = "ncurses gettext-native"
# vimdiff doesn't like busybox diff
RSUGGESTS_${PN} = "diffutils"
LICENSE = "vim"
LIC_FILES_CHKSUM = "file://../runtime/doc/uganda.txt;md5=b779e18be6ed77facc770691c967b8f8"

SRCPV = "${@d.getVar('PV',1).split('.')[0]}-${@d.getVar('PV',1).split('.')[1]}-${@d.getVar('PV',1).split('.')[2]}"

# Note: the github mirror is provided by the official MacVIM maintainer, it can therefor be assumed to be stable....

SRC_URI = "https://github.com/b4winckler/vim/archive/v${SRCPV}.tar.gz;downloadfilename=vim-${PV}.tar.gz \
           file://disable_acl_header_check.patch;patchdir=.. \
           file://vim-add-knob-whether-elf.h-are-checked.patch;patchdir=.. \
"
SRC_URI[md5sum] = "809380488dfdfeeb46424337eb088288"
SRC_URI[sha256sum] = "f2d8e0e1dfb0f7574d7add49b1b7b04040c536864f55a5d7fb6d8557df80a607"

S = "${WORKDIR}/${BPN}-${SRCPV}/src"

VIMDIR = "${BPN}${@d.getVar('PV',1).split('.')[0]}${@d.getVar('PV',1).split('.')[1]}"

inherit autotools update-alternatives
inherit autotools-brokensep

# vim configure.in contains functions which got 'dropped' by autotools.bbclass
do_configure () {
    rm -f auto/*
    touch auto/config.mk
    aclocal
    autoconf
    oe_runconf
    touch auto/configure
    touch auto/config.mk auto/config.h
}

#Available PACKAGECONFIG options are gtkgui, acl, x11, tiny
PACKAGECONFIG ??= ""
PACKAGECONFIG += "${@base_contains('DISTRO_FEATURES', 'acl', 'acl', '', d)}"
PACKAGECONFIG += "${@base_contains('DISTRO_FEATURES', 'selinux', 'selinux', '', d)}"

PACKAGECONFIG[gtkgui] = "--enable-gtk2-test --enable-gui=gtk2,--enable-gui=no,gtk+,"
PACKAGECONFIG[acl] = "--enable-acl,--disable-acl,acl,"
PACKAGECONFIG[x11] = "--with-x,--without-x,xt,"
PACKAGECONFIG[tiny] = "--with-features=tiny,--with-features=big,,"
PACKAGECONFIG[selinux] = "--enable-selinux,--disable-selinux,libselinux,"
PACKAGECONFIG[elfutils] = "--enable-elf-check,,elfutils,"

EXTRA_OECONF = " \
    --disable-gpm \
    --disable-gtktest \
    --disable-xim \
    --disable-netbeans \
    --with-tlib=ncurses \
    ac_cv_small_wchar_t=no \
    vim_cv_getcwd_broken=no \
    vim_cv_memmove_handles_overlap=yes \
    vim_cv_stat_ignores_slash=no \
    vim_cv_terminfo=yes \
    vim_cv_tgent=non-zero \
    vim_cv_toupper_broken=no \
    vim_cv_tty_group=world \
    STRIP=/bin/true \
"

do_install_append() {
    # Work around rpm picking up csh or awk or perl as a dep
    chmod -x ${D}${datadir}/${BPN}/${VIMDIR}/tools/vim132
    chmod -x ${D}${datadir}/${BPN}/${VIMDIR}/tools/mve.awk
    chmod -x ${D}${datadir}/${BPN}/${VIMDIR}/tools/*.pl

    # Install example vimrc from runtime files
    install -m 0644 ../runtime/vimrc_example.vim ${D}/${datadir}/${BPN}/vimrc

    echo "" >> ${D}/${datadir}/${BPN}/vimrc
    echo "\" set default colorscheme to something better readable" >> ${D}/${datadir}/${BPN}/vimrc
    echo "colorscheme desert" >> ${D}/${datadir}/${BPN}/vimrc
    echo "" >> ${D}/${datadir}/${BPN}/vimrc

    echo "\" deactivate mouse" >> ${D}/${datadir}/${BPN}/vimrc
    echo "set mouse=" >> ${D}/${datadir}/${BPN}/vimrc
    echo "" >> ${D}/${datadir}/${BPN}/vimrc

    echo "\" "backup files but delete after successfull write >> ${D}/${datadir}/${BPN}/vimrc
    echo "set nobackup" >> ${D}/${datadir}/${BPN}/vimrc
    echo "set writebackup" >> ${D}/${datadir}/${BPN}/vimrc
    echo "" >> ${D}/${datadir}/${BPN}/vimrc
}

PARALLEL_MAKEINST = ""

PACKAGES =+ "${PN}-common ${PN}-syntax ${PN}-help ${PN}-tutor ${PN}-vimrc"
FILES_${PN}-syntax = "${datadir}/${BPN}/${VIMDIR}/syntax"
FILES_${PN}-help = "${datadir}/${BPN}/${VIMDIR}/doc"
FILES_${PN}-tutor = "${datadir}/${BPN}/${VIMDIR}/tutor ${bindir}/${BPN}tutor"
FILES_${PN}-vimrc = "${datadir}/${BPN}/vimrc"
FILES_${PN}-data = "${datadir}/${BPN}"
FILES_${PN}-common = " \
    ${datadir}/${BPN}/${VIMDIR}/*.vim \
    ${datadir}/${BPN}/${VIMDIR}/autoload \
    ${datadir}/${BPN}/${VIMDIR}/colors \
    ${datadir}/${BPN}/${VIMDIR}/compiler \
    ${datadir}/${BPN}/${VIMDIR}/ftplugin \
    ${datadir}/${BPN}/${VIMDIR}/indent \
    ${datadir}/${BPN}/${VIMDIR}/keymap \
    ${datadir}/${BPN}/${VIMDIR}/lang \
    ${datadir}/${BPN}/${VIMDIR}/macros \
    ${datadir}/${BPN}/${VIMDIR}/plugin \
    ${datadir}/${BPN}/${VIMDIR}/print \
    ${datadir}/${BPN}/${VIMDIR}/spell \
    ${datadir}/${BPN}/${VIMDIR}/tools \
"

# Recommend that runtime data is installed along with vim
RRECOMMENDS_${PN} = "${PN}-syntax ${PN}-help ${PN}-tutor ${PN}-vimrc ${PN}-common"

ALTERNATIVE_${PN} = "vi"
ALTERNATIVE_TARGET[vi] = "${bindir}/${BPN}"
ALTERNATIVE_LINK_NAME[vi] = "${base_bindir}/vi"
ALTERNATIVE_PRIORITY[vi] = "100"
