DESCRIPTION = "Dropbear is a lightweight SSH and SCP implementation"
HOMEPAGE = "http://matt.ucc.asn.au/dropbear/dropbear.html"
SECTION = "console/network"

INC_PR = "r2"

# some files are from other projects and have others license terms:
#   public domain, OpenSSH 3.5p1, OpenSSH3.6.1p2, PuTTY
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3a5b0c2f0d0c49dfde9558ae2036683c"

inherit autotools systemd

DEPENDS = "zlib"
RPROVIDES_${PN} = "ssh sshd"
DEPENDS += "${@base_contains('DISTRO_FEATURES', 'pam', 'libpam', '', d)}"

SRC_URI = "http://matt.ucc.asn.au/dropbear/releases/dropbear-${PV}.tar.gz \
	         file://urandom-xauth-changes-to-options.h.patch \
		 file://dropbear-0.53.1-static_build_fix.patch \
	         file://configure.patch \
	         file://fix-2kb-keys.patch \
	         file://nopw-option.patch \
		 file://dropbearkey.service \
		 file://dropbear@.service \
            	 file://dropbear.socket \
                 ${@base_contains('DISTRO_FEATURES', 'pam', '${PAM_SRC_URI}', '', d)} "

PAM_SRC_URI = "file://dropbear-enable-pam.patch \
               file://dropbear-configuration-file.patch \
               file://dropbear"

CFLAGS_prepend = " -I. "
LD = "${CC}"

SBINCOMMANDS = "dropbear dropbearkey dropbearconvert"
BINCOMMANDS = "dbclient ssh scp"
EXTRA_OEMAKE = 'MULTI=1 SCPPROGRESS=1 PROGRAMS="${SBINCOMMANDS} ${BINCOMMANDS}"'
EXTRA_OECONF += "\
 ${@base_contains('DISTRO_FEATURES', 'pam', '--enable-pam', '--disable-pam', d)}"

SYSTEMD_SERVICE_${PN} = "dropbear.socket dropbearkey.service"

do_install() {
	install -d ${D}${sysconfdir} \
		   ${D}${sysconfdir}/default \
		   ${D}${sysconfdir}/dropbear \
                   ${D}${bindir} \
		   ${D}${sbindir} \
		   ${D}${localstatedir} \
		   ${D}${systemd_unitdir}/system

	install -m 755 dropbearmulti ${D}${sbindir}/
	ln -s ${sbindir}/dropbearmulti ${D}${bindir}/dbclient
	
	for i in ${SBINCOMMANDS}
	do
		ln -s ./dropbearmulti ${D}${sbindir}/$i
	done
	for i in ${DISTRO_FEATURES};
	do
		if [ ${i} = "pam" ];  then
			install -d ${D}${sysconfdir}/pam.d
			install -m 0644 ${WORKDIR}/dropbear  ${D}${sysconfdir}/pam.d/
		fi
	done

	install -m 644 ${WORKDIR}/*.service ${D}/${systemd_unitdir}/system
	install -m 644 ${WORKDIR}/*.socket ${D}/${systemd_unitdir}/system
}

inherit update-alternatives

ALTERNATIVE_PRIORITY = "20"
ALTERNATIVE_${PN} = "scp ssh"

ALTERNATIVE_TARGET = "${sbindir}/dropbearmulti"

pkg_postrm_append_${PN} () {
  if [ -f "${sysconfdir}/dropbear/dropbear_rsa_host_key" ]; then
        rm ${sysconfdir}/dropbear/dropbear_rsa_host_key
  fi
  if [ -f "${sysconfdir}/dropbear/dropbear_dss_host_key" ]; then
        rm ${sysconfdir}/dropbear/dropbear_dss_host_key
  fi
}
