
#####################################################
# For this class these variables can be set in the
# inheriting recipe:
#
# APPNAME             (default is "${PN}")
# APPVERSION          (default is "${PV}")
# REL_NAME            (default is "${APPNAME}")
# REL_VSN             (default is "${APPVERSION}")
# APP_PREFIX          (default is "/opt/apps")
# SYSCONFIG_PREFIX    (default is "${sysconfdir}/apps")
# CONFFILE            (default is "${SYSCONFIG_PREFIX}/${APPNAME}.conf")
#####################################################

APPNAME ?= "${PN}"
APPVERSION ?= "${PV}"

REL_NAME ?= "${APPNAME}"
REL_VSN ?= "${APPVERSION}"

APP_PREFIX ?= "/opt/apps"
SYSCONFIG_PREFIX ?= "${sysconfdir}/apps"

S ?= "${WORKDIR}"

FILES_${PN} += "${APP_PREFIX}/${APPNAME}/${APPVERSION}"
FILES_${PN}-dbg += "${APP_PREFIX}/${APPNAME}/${APPVERSION}/lib/*/priv/obj"
FILES_${PN}-dbg += "${APP_PREFIX}/${APPNAME}/${APPVERSION}/lib/*/priv/lib/.debug"
FILES_${PN}-dbg += "${APP_PREFIX}/${APPNAME}/${APPVERSION}/lib/*/priv/.debug"
FILES_${PN}-dbg += "${APP_PREFIX}/${APPNAME}/${APPVERSION}/erts*/bin/.debug"

FILES_${PN}-staticdev += "${APP_PREFIX}/${APPNAME}/${APPVERSION}/erts*/lib/lib*.a"
FILES_${PN}-staticdev += "${APP_PREFIX}/${APPNAME}/${APPVERSION}/erts*/lib/internal/lib*.a"

CONFFILE ?= "${SYSCONFIG_PREFIX}/${APPNAME}.conf"
CONFFILES_${PN} += "${CONFFILE}"

DEPENDS += "erlang elixir rebar3-native hex-native"

# packages based on this class are copying some files
# together instead of "compiling" them (mainly deps)
INSANE_SKIP_${PN} = "already-stripped"

do_compile() {

    export LC_ALL=en_US.UTF-8
    export MIX_ENV=prod
    # enable HEX_OFFLINE later after hex 0.16.0 will be fixed
    # https://github.com/hexpm/hex/issues/367
    #export HEX_OFFLINE=true
    export REPLACE_OS_VARS=true
    export MIX_REBAR3="${STAGING_BINDIR_NATIVE}/rebar3"
    export MIX_REBAR="${STAGING_BINDIR_NATIVE}/rebar"
    export STAGING_DIR_TARGET=${STAGING_DIR_TARGET}
    mix compile
    mix release --name=${REL_NAME} --env=prod
}

do_install() {
    RELEASE_TAR="_build/prod/rel/${REL_NAME}/releases/${REL_VSN}/${REL_NAME}.tar.gz"
    if [ -e ${RELEASE_TAR} ]
    then
        bbnote "${REL_NAME}: tar found at ${RELEASE_TAR}"
    else
        bbfatal "${REL_NAME}: tar file not found at ${RELEASE_TAR}"
        exit 1
    fi

    install -m 0755 -d "${D}/${APP_PREFIX}/${APPNAME}/${APPVERSION}/"
    tar xvz -C ${D}/${APP_PREFIX}/${APPNAME}/${APPVERSION} -f $RELEASE_TAR
    rm -vf ${D}/${APP_PREFIX}/${APPNAME}/${APPVERSION}/releases/${REL_VSN}/${REL_NAME}.tar.gz
    ln -s ${REL_NAME} "${D}/${APP_PREFIX}/${APPNAME}/${APPVERSION}/bin/rc"

    # distillery boot script changes CWD to release-dir.
    # we prevent that here, until we can upstream the fix of not doing it.
    relsh="${D}/${APP_PREFIX}/${APPNAME}/${APPVERSION}/releases/${REL_VSN}/${REL_NAME}.sh"
    [ -e $relsh ] && grep -q "^cd \"\$ROOTDIR\"$" $relsh && sed -i -e "s/^cd \"\$ROOTDIR\"$/# &/" $relsh

    # rename beam.smp to the actual app name and start it using a wrapper for proper process naming
    erts_release=$(basename ${D}${APP_PREFIX}/${APPNAME}/${APPVERSION}/erts-*)
    erts_base=${D}${APP_PREFIX}/${APPNAME}/${APPVERSION}/${erts_release}
    mv ${erts_base}/bin/beam.smp ${erts_base}/bin/${APPNAME}
    echo '#!/bin/sh\n' > ${erts_base}/bin/beam.smp
    echo "exec ${APPNAME} \"\$@\"" >> ${erts_base}/bin/beam.smp
    chmod 755 ${erts_base}/bin/beam.smp
    echo "${CONFFILE}" > ${D}/${APP_PREFIX}/${APPNAME}/${APPVERSION}/CONFPATH
    # fix permissions
    chmod 0755 "${D}/${APP_PREFIX}/${APPNAME}/${APPVERSION}/releases/${REL_VSN}/${REL_NAME}.sh"
    chmod 0755 "${D}/${APP_PREFIX}/${APPNAME}/${APPVERSION}/bin/${REL_NAME}"
    chmod 0755 "${D}/${APP_PREFIX}/${APPNAME}/${APPVERSION}/bin/nodetool"
}

