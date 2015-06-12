DESCRIPTION = "Universal API Multiplexer"
SECTION = "net"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"

PR = "r3"

APPNAME = "unimux"
APPVERSION = "${PV}"

REL_NAME = "${APPNAME}"
REL_VSN = "0.1.3"

SRC_URI = "git://github.com/carosio/unimux.git;protocol=git;name=unimux;destsuffix=git-${REL_NAME}"

SRC_URI += "file://${APPNAME}.service"

SRCREV_unimux = "ff77c86fba1a0966e2bd1771375f3dbe70ffd92f"

S = "${WORKDIR}/git-${REL_NAME}"

include urilock_${REL_NAME}_${REL_VSN}.inc

DEPENDS += "elixir-native elixir rebar-native"

# deps to be copied into release
DEPENDS += "avahi erlang-lager-journald-backend"

do_compile() {
    cp -avl ${WORKDIR}/git-deps ./deps
    for mixdep in ${WORKDIR}/hex-deps/*; do
        mixdepbase="`basename $mixdep`"
        mkdir -pv deps/$mixdepbase
        tar xz -C deps/$mixdepbase -f $mixdep/contents.tar.gz
    done

    export LC_ALL=en_US.UTF-8
    export MIX_ENV=release

    # explicitly use TARGET erts instead of the erlang-native provided one:
    TARGET_ERTS="${STAGING_DIR_TARGET}/usr/lib/erlang"
    mkdir -pv rel

    echo "{include_erts, \"${TARGET_ERTS}\"}." > rel/relx.config

    echo "{lib_dirs, [" >> rel/relx.config
        echo "\"${STAGING_DIR_TARGET}/usr/lib/erlang/lib/lager_journald_backend-*\"," >> rel/relx.config
        echo "\"${STAGING_DIR_TARGET}/usr/lib/erlang/lib/ejournald-*\"" >> rel/relx.config
    echo "]}." >> rel/relx.config

    echo "generated relx.config:"
    echo "==============="
    cat rel/relx.config
    echo "==============="

    mix do deps.compile, compile, release
}

CAROS_APP_PREFIX = "/usr/caros-apps"
CAROS_SYSCONFIG_PREFIX = "${sysconfdir}"

FILES_${PN} += "${CAROS_APP_PREFIX}/${APPNAME}/${APPVERSION}"
FILES_${PN}-dbg += "${CAROS_APP_PREFIX}/${APPNAME}/${APPVERSION}/lib/*/priv/obj"
FILES_${PN}-dbg += "${CAROS_APP_PREFIX}/${APPNAME}/${APPVERSION}/lib/*/priv/lib/.debug"
FILES_${PN}-dbg += "${CAROS_APP_PREFIX}/${APPNAME}/${APPVERSION}/lib/*/priv/.debug"
FILES_${PN}-dbg += "${CAROS_APP_PREFIX}/${APPNAME}/${APPVERSION}/erts*/bin/.debug"

FILES_${PN}-staticdev += "${CAROS_APP_PREFIX}/${APPNAME}/${APPVERSION}/erts*/lib/lib*.a"
FILES_${PN}-staticdev += "${CAROS_APP_PREFIX}/${APPNAME}/${APPVERSION}/erts*/lib/internal/lib*.a"

CONFFILES_${PN} += "${CAROS_SYSCONFIG_PREFIX}/${APPNAME}.conf"

inherit systemd

SYSTEMD_AUTO_ENABLE = "disable"

SYSTEMD_SERVICE_${PN} = "caros-${APPNAME}-${APPVERSION}.service"


do_install() {
    install -m 0755 -d "${D}/${CAROS_APP_PREFIX}/${APPNAME}/${APPVERSION}/"
    tar xvz -C ${D}/${CAROS_APP_PREFIX}/${APPNAME}/${APPVERSION} -f rel/${REL_NAME}/${REL_NAME}-${REL_VSN}.tar.gz
    ln -s ${REL_NAME} "${D}/${CAROS_APP_PREFIX}/${APPNAME}/${APPVERSION}/bin/rc"

    install -m 0755 -d "${D}/${CAROS_SYSCONFIG_PREFIX}"
    install -m 0644 ${S}/config/${APPNAME}.conf ${D}/${CAROS_SYSCONFIG_PREFIX}/${APPNAME}.conf

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${WORKDIR}/${APPNAME}.service ${D}${systemd_unitdir}/system/caros-${APPNAME}-${APPVERSION}.service
    sed -i "s/@@VERSION@@/${APPVERSION}/" ${D}${systemd_unitdir}/system/caros-${APPNAME}-${APPVERSION}.service
}

