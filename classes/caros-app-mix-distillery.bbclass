
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

DEPENDS += "elixir-native rebar-native rebar3-native hex-native"

# packages based on this class are copying some files
# together instead of "compiling" them (mainly deps)
INSANE_SKIP_${PN} = "already-stripped"

do_compile() {
    # because of our pre-downloading of all deps into deps/
    # (similar to vendoring), there is no need for further
    # downloading or looking up dependencies from the net.
    # unfortunately mix/hex still fails on missing hex-registry
    # to workaround that we create an "empty" one:
    export HEX_OFFLINE=true
    export HEX_HOME=${WORKDIR}/hex-home
    mkdir -pv $HEX_HOME
    echo 'defmodule Hex do' > ${WORKDIR}/createreg.ex
    echo '    def ets_registry(path) do' >> ${WORKDIR}/createreg.ex
    echo '      tid = :ets.new(:hex_ets_registry, [])' >> ${WORKDIR}/createreg.ex
    echo '      :ets.insert(tid, {:"$$version$$", 4})' >> ${WORKDIR}/createreg.ex
    echo '      :ets.insert(tid, {:"$$installs2$$", []})' >> ${WORKDIR}/createreg.ex
    echo '      :ok = :ets.tab2file(tid, String.to_char_list(path))' >> ${WORKDIR}/createreg.ex
    echo '    end' >> ${WORKDIR}/createreg.ex
    echo 'end' >> ${WORKDIR}/createreg.ex

    elixir -r ${WORKDIR}/createreg.ex -e 'Hex.ets_registry("'${HEX_HOME}'/registry.ets")'

    if [ -d ${WORKDIR}/git-deps ]
    then
        cp -navl ${WORKDIR}/git-deps ./deps
    fi
    for mixdep in ${WORKDIR}/hex-deps/*; do
        mixdepbase="`basename $mixdep`"
        if [ ! -e deps/$mixdepbase ] ; then
            mkdir -pv deps/$mixdepbase
            if [ -e $mixdep/contents.tar.gz ] ; then
                tar xz -C deps/$mixdepbase -f $mixdep/contents.tar.gz
            else
                cp -navl $mixdep/$mixdepbase-*/* deps/$mixdepbase
            fi
        fi
    done

    export LC_ALL=en_US.UTF-8
    export MIX_ENV=prod
    export MIX_REBAR3="${STAGING_BINDIR_NATIVE}/rebar3"
    export MIX_REBAR="${STAGING_BINDIR_NATIVE}/rebar"

    mix compile
    mix release --name=${REL_NAME} --env=prod
}

do_install() {
    if [ -e _build/prod/rel/${REL_NAME}/releases/${REL_VSN}/${REL_NAME}.tar.gz ]
    then
        RELEASE_TAR="_build/prod/rel/${REL_NAME}/releases/${REL_VSN}/${REL_NAME}.tar.gz"
        bbnote "tar found at _build/prod/rel/${REL_NAME}/releases/${REL_VSN}/${REL_NAME}.tar.gz"
    elif [ -e rel/${REL_NAME}/${REL_NAME}-${REL_VSN}.tar.gz ]
    then
        RELEASE_TAR="_build/prod/rel/${REL_NAME}/${REL_NAME}-${REL_VSN}.tar.gz"
        bbnote "tar found at _build/prod/rel/${REL_NAME}-${REL_VSN}.tar.gz"
    else
        bbfatal "${REL_NAME}: tar file not found"
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

    if [ -f ${S}/config/${REL_NAME}.conf ];
    then
        install -m 0755 -d "${D}/${SYSCONFIG_PREFIX}"
        install -m 0644 ${S}/config/${REL_NAME}.conf ${D}${CONFFILE}
        echo >> ${D}${CONFFILE}
        echo "# Choose the logging level for the journal backend." >> ${D}${CONFFILE}
        echo "# Allowed values: emerg, alert, crit, error, warning, notive, info, debug, false" >> ${D}${CONFFILE}
        echo "log.journal.level = info" >> ${D}${CONFFILE}
        echo "# Choose the logging level for the console backend." >> ${D}${CONFFILE}
        echo "# Allowed values: info, error, false" >> ${D}${CONFFILE}
        echo "log.console.level = false" >> ${D}${CONFFILE}

        echo "${CONFFILE}" > ${D}/${APP_PREFIX}/${APPNAME}/${APPVERSION}/CONFPATH
    fi;

    # fix permissions
    chmod 0755 "${D}/${APP_PREFIX}/${APPNAME}/${APPVERSION}/releases/${REL_VSN}/${REL_NAME}.sh"
    chmod 0755 "${D}/${APP_PREFIX}/${APPNAME}/${APPVERSION}/bin/${REL_NAME}"
    chmod 0755 "${D}/${APP_PREFIX}/${APPNAME}/${APPVERSION}/bin/nodetool"
}
