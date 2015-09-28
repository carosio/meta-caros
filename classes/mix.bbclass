
#####################################################
# For this class these variables must be set:
#
#
# APPNAME             (default is "${PN}")
# APPVERSION          (default is "${PV}")
# REL_NAME            (default is "${APPNAME}")
# REL_VSN             (default is "${APPVERSION}")
# APP_PREFIX          (default is "/opt/apps")
# SYSCONFIG_PREFIX    (default is "${sysconfdir}/apps")
# SYSTEMD_UNIT_NAME   (default is "${APPNAME}")
# SYSTEMD_AUTO_ENABLE (default is "disable")
#####################################################

APPNAME ?= "${PN}"
APPVERSION ?= "${PV}"

REL_NAME ?= "${APPNAME}"
REL_VSN ?= "${APPVERSION}"

APP_PREFIX ?= "/opt/apps"
SYSCONFIG_PREFIX ?= "${sysconfdir}/apps"

CAROS_APPCTL = "/opt/tposs/libexec/appctl.sh"

S ?= "${WORKDIR}"

FILES_${PN} += "${APP_PREFIX}/${APPNAME}/${APPVERSION}"
FILES_${PN}-dbg += "${APP_PREFIX}/${APPNAME}/${APPVERSION}/lib/*/priv/obj"
FILES_${PN}-dbg += "${APP_PREFIX}/${APPNAME}/${APPVERSION}/lib/*/priv/lib/.debug"
FILES_${PN}-dbg += "${APP_PREFIX}/${APPNAME}/${APPVERSION}/lib/*/priv/.debug"
FILES_${PN}-dbg += "${APP_PREFIX}/${APPNAME}/${APPVERSION}/erts*/bin/.debug"

FILES_${PN}-staticdev += "${APP_PREFIX}/${APPNAME}/${APPVERSION}/erts*/lib/lib*.a"
FILES_${PN}-staticdev += "${APP_PREFIX}/${APPNAME}/${APPVERSION}/erts*/lib/internal/lib*.a"

DEPENDS += "avahi erlang-lager-journald-backend elixir-native elixir rebar-native"

inherit systemd

SYSTEMD_UNIT_NAME ?= "${APPNAME}@${APPVERSION}:default"
SYSTEMD_AUTO_ENABLE ?= "disable"
SYSTEMD_SERVICE_${PN} = "${SYSTEMD_UNIT_NAME}@.service"

def gen_deps(d):
    import sys
    import re
    import urllib2
    import hashlib

    mixlock = open("{dir}/mix.lock".format(dir=d.expand("${S}"))).read()

    mixlock = mixlock.replace(' ', '').replace('\n', '')[2:-1]
    mixlock = mixlock.replace('[', '<').replace(']', '>')
    mixlock = mixlock.replace('{', '[').replace('}', ']')
    mixlock = mixlock.replace('<', '{').replace('>', '}')
    mixlock = mixlock.replace(':git', '"git"').replace(':hex', '"hex"')
    mixlock = mixlock.replace('tag:', 'ref:')
    for git_param in ('branch', 'ref'):
        mixlock = mixlock.replace('%s:'%git_param, '"%s":'%git_param)
    mixlock = "".join(('{', mixlock, '}'))

    mixlock = re.compile(',:([^,]*),').sub(', "\\1", ', mixlock)

    mixdeps = eval(mixlock)

    def checksum(uri):
        maxbyte = 256*1024*1024
        bufsize = 16*1024
        remote = urllib2.urlopen(uri)
        hasher1 = hashlib.md5()
        hasher2 = hashlib.sha256()
        readbytes = 0
        while True:
            data = remote.read(bufsize)
            readbytes += bufsize
            if not data: break
            if readbytes > maxbyte: raise Exception("checksum limit")
            hasher1.update(data)
            hasher2.update(data)
        return (hasher1.hexdigest(), hasher2.hexdigest())

    bb.debug(2, "generating dependencies from mix.lock")

    for key,value in mixdeps.iteritems():
        if value[0] == 'git':
            git_url = value[1]
            if git_url.startswith("https://"):
                git_url = "".join(("git://",git_url[8:],";protocol=https"))
            # convert "git@github.com:travelping/hello" --> "git://github.com/travelping/hello;protocol=ssh"
            git_url = re.sub("^([^:/]+@[^:]+):(.+)$", "git://\\1/\\2;protocol=ssh", git_url)
            git_src_uri = ";".join((
                git_url,
                'branch=%s'%(value[3].get('branch') or 'master'),
                'destsuffix=git-deps/%s'%key,
                'name=%s'%key))
            d.appendVar('SRC_URI', " %s "%git_src_uri)
            d.setVar('SRCREV_%s'%key, value[2])
            bb.debug(2, 'SRC_URI %s '%git_src_uri)
            bb.debug(2, 'SRCREV_%s %s'%(key, value[2]))
        if value[0] == 'hex':
            hex_uri = "https://s3.amazonaws.com/s3.hex.pm/tarballs/{name}-{version}.tar".format(
                    name=value[1], version=value[2])
            hex_src_uri = ";".join((hex_uri,
                'subdir=hex-deps/%s'%key,
                'name=%s'%key))
            d.appendVar('SRC_URI', " %s "%hex_src_uri)
            md5, sha256 = checksum(hex_uri)
            d.setVarFlag("SRC_URI", "%s.md5sum"%key, md5)
            d.setVarFlag("SRC_URI", "%s.sha256sum"%key, sha256)
            bb.debug(2, 'SRC_URI %s '%hex_src_uri)
            bb.debug(2, "SRC_URI %s.md5sum %s"%(key, md5))
            bb.debug(2, "SRC_URI %s.sha256sum %s"%(key, sha256))

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

    if [ -e rel/vm.args ]
    then
        # if there is a costum vm.args we just remove the node name and cookie option
        sed -i -e 's/^[ \t]*-s\?name\>/# disabled by exrm-boot: &/' rel/vm.args
        sed -i -e '/-setcookie/s/^/## removed: /' rel/vm.args        
        echo "generated vm.args:"
        echo "==============="
        cat rel/vm.args
        echo "==============="
    else
        # else there is the default vm.args which just includes the node name and cookie option
        # hence we just put in an empty vm.args which is then used by exrm as default 
        echo "default vm.args will be overwritten by an empty vm.args and filled by appctl at boot time."
        touch rel/vm.args
    fi

    mix do deps.compile, compile, release
}

do_install() {
    if [ -e rel/${REL_NAME}/releases/${REL_VSN}/${REL_NAME}.tar.gz ]
    then
        TAR_DIR="rel/${REL_NAME}/releases/${REL_VSN}/${REL_NAME}.tar.gz"
        bbnote "tar found at rel/${REL_NAME}/releases/${REL_VSN}/${REL_NAME}.tar.gz"
    elif [ -e rel/${REL_NAME}/${REL_NAME}-${REL_VSN}.tar.gz ]
    then
        TAR_DIR="rel/${REL_NAME}/${REL_NAME}-${REL_VSN}.tar.gz"
        bbnote "tar found at rel/${REL_NAME}-${REL_VSN}.tar.gz"
    else
        bbfatal "${REL_NAME}: tar file not found"
        exit 1
    fi

    install -m 0755 -d "${D}/${APP_PREFIX}/${APPNAME}/${APPVERSION}/"
    tar xvz -C ${D}/${APP_PREFIX}/${APPNAME}/${APPVERSION} -f $TAR_DIR
    ln -s ${REL_NAME} "${D}/${APP_PREFIX}/${APPNAME}/${APPVERSION}/bin/rc"

    install -m 0755 -d "${D}/${SYSCONFIG_PREFIX}"
    install -m 0644 ${S}/config/${APPNAME}.conf ${D}/${SYSCONFIG_PREFIX}/${APPNAME}.conf
    echo >> ${D}/${SYSCONFIG_PREFIX}/${APPNAME}.conf
    echo "log.journal.level = info" >> ${D}/${SYSCONFIG_PREFIX}/${APPNAME}.conf
    echo "log.console.level = false" >> ${D}/${SYSCONFIG_PREFIX}/${APPNAME}.conf

    install -d ${D}${systemd_unitdir}/system/
    install -m 0644 ${MIX_CLASS_FILES}/app-template@.service ${D}${systemd_unitdir}/system/${SYSTEMD_UNIT_NAME}@.service
    sed -i "s|@@DESCRIPTION@@|${DESCRIPTION}|" ${D}${systemd_unitdir}/system/${SYSTEMD_UNIT_NAME}@.service
    sed -i "s|@@APPNAME@@|${APPNAME}|" ${D}${systemd_unitdir}/system/${SYSTEMD_UNIT_NAME}@.service
    sed -i "s|@@APP_PREFIX@@|${APP_PREFIX}|" ${D}${systemd_unitdir}/system/${SYSTEMD_UNIT_NAME}@.service
    sed -i "s|@@CAROS_APPCTL@@|${CAROS_APPCTL}|" ${D}${systemd_unitdir}/system/${SYSTEMD_UNIT_NAME}@.service
}

python do_mix_deps() {
        gen_deps(d)

        src_uri = (d.getVar('SRC_URI', True) or "").split()
        if len(src_uri) == 0:
            return

        rootdir = d.getVar('WORKDIR', True)

        try:
            fetcher = bb.fetch2.Fetch(src_uri, d)
            fetcher.download()
            fetcher.unpack(rootdir)
        except bb.fetch2.BBFetchException as e:
            raise bb.build.FuncFailed(e)
}

addtask mix_deps after do_unpack before do_compile
