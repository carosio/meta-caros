
#####################################################
# For this class these variables must be set:
#
# 
# APPNAME           (default is "${PN}")
# APPVERSION        (default is "${PV}")
# REL_NAME          (default is "${APPNAME}")
# REL_VSN           (default is "${APPVERSION}")
# APP_PREFIX        (default is "/opt/apps")
# SYSCONFIG_PREFIX  (default is "${sysconfdir}/apps")
#####################################################

APPNAME ?= "${PN}"
APPVERSION ?= "${PV}"

REL_NAME ?= "${APPNAME}"
REL_VSN ?= "${APPVERSION}"

APP_PREFIX ?= "/opt/apps"
SYSCONFIG_PREFIX ?= "${sysconfdir}/apps"

FILES_${PN} += "${APP_PREFIX}/${APPNAME}/${APPVERSION}"
FILES_${PN}-dbg += "${APP_PREFIX}/${APPNAME}/${APPVERSION}/lib/*/priv/obj"
FILES_${PN}-dbg += "${APP_PREFIX}/${APPNAME}/${APPVERSION}/lib/*/priv/lib/.debug"
FILES_${PN}-dbg += "${APP_PREFIX}/${APPNAME}/${APPVERSION}/lib/*/priv/.debug"
FILES_${PN}-dbg += "${APP_PREFIX}/${APPNAME}/${APPVERSION}/erts*/bin/.debug"

FILES_${PN}-staticdev += "${APP_PREFIX}/${APPNAME}/${APPVERSION}/erts*/lib/lib*.a"
FILES_${PN}-staticdev += "${APP_PREFIX}/${APPNAME}/${APPVERSION}/erts*/lib/internal/lib*.a"

DEPENDS += "avahi erlang-lager-journald-backend elixir-native elixir rebar-native"

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
    mixlock = mixlock.replace('tag', 'ref')
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


    for key,value in mixdeps.iteritems():
        if value[0] == 'git':
            git_url = value[1]
            if git_url.startswith("https://"):
                git_url = "".join(("git://",git_url[8:],";protocol=https"))
            # convert "git@gitlab.tpip.net:hello" --> "git:gitlab.tpip.net/hellp;protocol=ssh"
            git_url = re.sub("^([^:/]+@[^:]+):(.+)$", "git://\\1/\\2;protocol=ssh", git_url)
            git_src_uri = ";".join((
                git_url,
                'branch=%s'%(value[3].get('branch') or 'master'),
                'destsuffix=git-deps/%s'%key,
                'name=%s'%key))
            d.appendVar('SRC_URI', " %s "%git_src_uri)
            d.setVar('SRCREV_%s'%key, value[2])
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

do_install() {
    install -m 0755 -d "${D}/${APP_PREFIX}/${APPNAME}/${APPVERSION}/"
    tar xvz -C ${D}/${APP_PREFIX}/${APPNAME}/${APPVERSION} -f rel/${REL_NAME}/releases/${REL_VSN}/${REL_NAME}.tar.gz
    ln -s ${REL_NAME} "${D}/${APP_PREFIX}/${APPNAME}/${APPVERSION}/bin/rc"
}

python do_mix_deps() {
        gen_deps(d)
        src_uri = (d.getVar('SRC_URI', True) or "").split()
        fetcher = bb.fetch2.Fetch(src_uri, d)
        fetcher.download()
        rootdir = d.getVar('WORKDIR', True)
        fetcher.unpack(rootdir)
}


addtask mix_deps after do_unpack before do_compile
