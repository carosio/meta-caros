SUMMARY = "glusterfs - distributed scale out filesystem"
DESCRIPTION = "Gluster is a distributed scale out filesystem that allows \
    rapid provisioning of additional storage based on your storage \
    consumption needs. It incorporates automatic failover as a primary \
    feature. All of this is accomplished without a centralized metadata server."
HOMEPAGE = "http://www.gluster.org/"
SECTION = "console/network"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING-GPLV2;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PR = "r2"

SRC_URI = "http://download.gluster.org/pub/gluster/glusterfs/3.7/${PV}/${BPN}-${PV}.tar.gz \
	   file://configure-forign.patch \
	   file://python-cross.patch \
	   file://gluster-tmpfiles"
SRC_URI[md5sum] = "0b565f92a467f5aafab38c8343f0caa0"
SRC_URI[sha256sum] = "fc367f153cb528f96501abaae537a7bc9705aeb5c7ea4e448679b0cd65642b34"

DEPENDS = "liburcu libxml2 bison-native flex-native fuse openssl python-native python readline \
	   zlib util-linux acl attr glib-2.0"

inherit autotools pythonnative pkgconfig systemd

CACHED_CONFIGUREVARS = "ac_cv_lib_lex=no \
			ac_cv_file__etc_debian_version=no \
			ac_cv_file__etc_redhat_release=no \
			ac_cv_file__etc_SuSE_release=no"
EXTRA_OECONF = "--disable-fusermount \
		--disable-glupy"
EXTRA_OECONF += "${@base_contains('DISTRO_FEATURES', 'systemd', '--with-systemddir=${systemd_unitdir}/system', '--without-systemddir', d)}"

PACKAGECONFIG ??= "georeplication"
PACKAGECONFIG[debug] = "--enable-debug,--disable-debug,,"
PACKAGECONFIG[georeplication] = "--enable-georeplication,--disable-georeplication,,"

PACKAGES = "${PN}-dbg ${PN}-doc ${PN}-dev \
	    ${PN}-geo-replication ${PN}-ganesha \
	    ${PN}-client ${PN}-server \
	    ${PN}-common"

SYSTEMD_PACKAGES = "${PN}-server"
SYSTEMD_SERVICE_${PN}-server = "glusterd.service"

RDEPENDS_${PN}-common = "bash attr kernel-module-fuse "
KERNEL_MODULE_AUTOLOAD += "fuse"
RDEPENDS_${PN}-ganesha = "bash"

PRIVATE_LIBS = "access-control.so addr.so afr.so api.so arbiter.so barrier.so \
		bit-rot.so bitrot-stub.so cdc.so changelog.so changetimerecorder.so \
		client.so crypt.so dht.so disperse.so distribute.so ec.so error-gen.so fuse.so \
		ganesha.so gfid-access.so glusterd.so index.so io-cache.so \
		io-stats.so io-threads.so locks.so login.so mac-compat.so marker.so md-cache.so \
		meta.so nufa.so open-behind.so posix-acl.so posix-locks.so posix.so \
		prot_client.so prot_dht.so prot_server.so pump.so qemu-block.so \
		quick-read.so quiesce.so quotad.so quota.so read-ahead.so \
		readdir-ahead.so read-only.so replicate.so rot-13.so server.so \
		shard.so snapview-client.so snapview-server.so socket.so \
		stat-prefetch.so stripe.so switch.so symlink-cache.so template.so \
		tier.so trace.so trash.so upcall.so worm.so write-behind.so"

do_install_append() {
    # These are plug-ins, so they don't need libtool configs.
    find ${D}${libdir}/glusterfs/${PV} -name '*.la' -exec rm -f '{}' ';'

    rm -rf ${D}/run ${D}/var/run ${D}/var/log  ${D}/var/volatile

    # The RPM spec file creates these directories.
    install -d -m 0755 ${D}${sysconfdir}/tmpfiles.d
    install ${WORKDIR}/gluster-tmpfiles ${D}${sysconfdir}/tmpfiles.d/99_glusterfs.conf

    rm -f ${D}${sysconfdir}/glusterfs/glusterfs-logrotate \
       	  ${D}${sysconfdir}/glusterfs/group-virt.example

    install -D -p -m 0644 ${S}/extras/glusterfs-logrotate ${D}${sysconfdir}/logrotate.d/glusterfs
}

pkg_postinst_${PN}-common () {
    mkdir -p /var/log/glusterfs
    mkdir -p /var/run/gluster

    (
        echo
        echo -e "\e[1;31m-----------------------------------------"
        echo -e "\e[1;31m| To finish the GlusterFS installation, |"
        echo -e "\e[1;31m| manually load the fuse kernel module: |"
        echo -e "\e[1;31m|                                       |"
        echo -e "\e[1;31m| modprobe fuse                         |"
        echo -e "\e[1;31m|                                       |"
        echo -e "\e[1;31m| or reboot this machine.               |"
        echo -e "\e[1;31m-----------------------------------------"
        echo -e "\033[0m"
    )
}

# Allow plug-in symlinks.
INSANE_SKIP_${PN}-common += "dev-so"

FILES_${PN}-dbg += "${libexecdir}/${PV}/*/.debug \
		    ${libexecdir}/${PV}/*/*/.debug \
		    ${libexecdir}/${PV}/*/*/*/.debug \
		    ${libexecdir}/${BPN}/.debug \
		    ${libexecdir}/${BPN}/gfind_missing_files/.debug \
		    ${datadir}/${BPN}/scripts/.debug"

FILES_${PN}-common = "${sbindir}/glusterfsd \
		      ${libdir}/* \
		      ${datadir}/glusterfs/scripts \
		      ${sysconfdir}/tmpfiles.d/99_glusterfs.conf"

FILES_${PN}-client = "${sbindir}/mount.glusterfs \
		      ${sbindir}/glusterfs \
		      /sbin \
		      ${bindir}/fusermount-glusterfs"

FILES_${PN}-server = "${sysconfdir}/logrotate.d/glusterfs \
		      ${sbindir}/gluster \
		      ${sbindir}/glusterd \
		      ${sbindir}/glfsheal \
		      ${libexecdir}/glusterfs/glusterfind \
		      ${bindir}/glusterfind \
		      ${libexecdir}/glusterfs/peer_add_secret_pub \
		      ${localstatedir}/lib/glusterd \
		      ${sysconfdir}/glusterfs/glusterd.vol \
		      ${sysconfdir}/glusterfs/logger.conf.example \
		      ${sysconfdir}/glusterfs/glusterfs-georep-logrotate \
		      ${sysconfdir}/glusterfs/gluster-rsyslog-7.2.conf \
		      ${sysconfdir}/glusterfs/gluster-rsyslog-5.8.conf \
		      ${sbindir}/snap_scheduler.py \
		      ${sbindir}/gcron.py"

CONFFILES_${PN}-server = "${sysconfdir}/logrotate.d/glusterfs"

FILES_${PN}-geo-replication = "${libexecdir}/glusterfs/gsyncd \
			       ${libexecdir}/glusterfs/python/syncdaemon/* \
			       ${libexecdir}/glusterfs/gverify.sh \
			       ${libexecdir}/glusterfs/set_geo_rep_pem_keys.sh \
			       ${libexecdir}/glusterfs/peer_gsec_create \
			       ${libexecdir}/glusterfs/peer_mountbroker \
			       ${sharedstatedir}/glusterd/hooks \
			       ${sharedstatedir}/glusterd/hooks/1 \
			       ${sharedstatedir}/glusterd/hooks/1/gsync-create \
			       ${sharedstatedir}/glusterd/hooks/1/gsync-create/post \
			       ${sharedstatedir}/glusterd/hooks/1/gsync-create/post/S56glusterd-geo-rep-create-post.sh \
			       ${datadir}/glusterfs/scripts/get-gfid.sh \
			       ${datadir}/glusterfs/scripts/slave-upgrade.sh \
			       ${datadir}/glusterfs/scripts/gsync-upgrade.sh \
			       ${datadir}/glusterfs/scripts/generate-gfid-file.sh \
			       ${datadir}/glusterfs/scripts/gsync-sync-gfid \
			       ${libexecdir}/glusterfs/gfind_missing_files \
			       ${sbindir}/gfind_missing_files"


FILES_${PN}-ganesha = "${sysconfdir}/ganesha \
		       ${libexecdir}/ganesha \
		       ${prefix}/lib/ocf/resource.d/heartbeat"
