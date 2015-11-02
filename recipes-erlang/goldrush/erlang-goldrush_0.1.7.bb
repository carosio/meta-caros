DESCRIPTION = "Small, Fast event processing and monitoring for Erlang/OTP applications."
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7b9f7f44f40906370137171b3437c818"

PR = "r3"

SRC_URI = "https://github.com/DeadZen/goldrush/archive/${PV}.tar.gz;downloadfilename=goldrush-${PV}.tar.gz \
	   file://add-tetrapak.patch;apply=yes"

SRC_URI[md5sum] = "3869ba352a03ce1a5cd6c1c6f3daf26d"
SRC_URI[sha256sum] = "4423756af9808421dc68097eda66fbdc189b0415795f3373737870007a6015f6"


S = "${WORKDIR}/goldrush-${PV}"

inherit tetrapak

python () {
    erlang_def_package("goldrush", "goldrush-*", "ebin", "src priv LICENSE README.md README.org", d)
}
