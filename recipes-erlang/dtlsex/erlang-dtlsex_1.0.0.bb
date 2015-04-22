DESCRIPTION = "Erlang/OTP DTLS Stack"
SECTION = "lib"
LICENSE = "ErlPL-1.1"
LIC_FILES_CHKSUM = "file://src/dtlsex.erl;endline=20;md5=8110eccfc8036a42d94ba0599233d113"

PR = "r0"

SRC_URI = "https://github.com/RoadRunnr/dtlsex/archive/v${PV}.tar.gz;downloadfilename=dtlsex-${PV}.tar.gz"
SRC_URI[md5sum] = "3d429834a58fffd8397f6a38fcdc6b46"
SRC_URI[sha256sum] = "79d952cd5eb40a03376ce7abc7ae11e415a488c29048ee835989f74c7bb26be2"

S = "${WORKDIR}/dtlsex-${PV}"

inherit tetrapak

python () {
    erlang_def_package("dtlsex", "dtlsex-*", "ebin", "src include", d)
}

