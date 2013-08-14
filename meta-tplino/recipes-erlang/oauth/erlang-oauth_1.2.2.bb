DESCRIPTION = "An Erlang OAuth 1.0 implementation"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://License.txt;md5=bf482687f6a03a670436598bcf544644"

SRCREV="109305111efd0f181c10819779ea554bb8096063"
PR = "r2"
PV-orig := "${PV}" 
PV = "${PV-orig}-${PR}-${SRCPV}"

SRC_URI = "git://github.com/refuge/erlang-oauth.git;protocol=git \
	   file://add-tetrapak.patch;apply=yes"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("oauth", "oauth-*", "ebin priv", "License.txt src include", d)
}
