DESCRIPTION = "Nginx is a free, open-source, high-performance HTTP server and reverse proxy, as well as an IMAP/POP3 proxy server."
HOMEPAGE = "http://wiki.nginx.org"
SECTION = "net"
PRIORITY = "optional"
LICENSE = "BSD"

inherit systemd

LIC_FILES_CHKSUM = "file://LICENSE;md5=3845852aedfa8d6d7765f55d06cc3ebd"

PR = "r1"

SRC_URI = "http://nginx.org/download/nginx-${PV}.tar.gz \
           file://allow-cross.patch \
           file://nginx.service"

SRC_URI[md5sum] = "f6899825e7a8deadba4948ff84515ad6"
SRC_URI[sha256sum] = "ed501fc6d0eff9d3bc1049cc1ba3a3ac8c602de046acb2a4c108392bbfa865ea"

DEPENDS = "libpcre logrotate openssl"

FILES_${PN} += " /usr/local/nginx \
                 /run "

SYSTEMD_SERVICE_${PN} = "nginx.service"

do_configure() {
    export cross_compiling="yes"
    ${S}/configure \
    --with-cc='${CC}' \
    --with-cc-opt="-Wl,--hash-style=gnu" \
    --sbin-path=${sbindir}/nginx \
    --pid-path=${localstatedir}/run/nginx.pid \
    --lock-path=${localstatedir}/lock/nginx.lock \
    --error-log-path=${localstatedir}/nginx/error \
    --conf-path=${sysconfdir}/nginx/nginx.conf \
    --http-log-path=${localstatedir}/nginx/access \
    --http-client-body-temp-path=${localstatedir}/lib/nginx/client_body_temp \
    --http-proxy-temp-path=${localstatedir}/lib/nginx/proxy_temp \
    --http-fastcgi-temp-path=${localstatedir}/lib/nginx/fastcgi_temp \
    --http-uwsgi-temp-path=${localstatedir}/lib/nginx/uwsgi_temp \
    --http-scgi-temp-path=${localstatedir}/lib/nginx/scgi_temp \
    --with-http_ssl_module
}

do_install() {
    install -d ${D}${localstatedir}/lib/nginx \
               ${D}${localstatedir}/nginx \
               ${D}${sysconfdir}/logrotate.d \
               ${D}${systemd_unitdir}/system

    install -m 0644 ${THISDIR}/files/nginx.logrotate ${D}${sysconfdir}/logrotate.d
    install -m 644 ${WORKDIR}/*.service ${D}/${systemd_unitdir}/system

    oe_runmake DESTDIR=${D} install
}

