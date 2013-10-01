DESCRIPTION = "HTTP client application"
SECTION = "devel"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://BSD_LICENSE;md5=357cce4ac3d0490701422421e7b58807"

SRCREV="8c46b108bb648119b90433f9c60ed36bb25e29cd"
PR = "r1"

SRC_URI = "git://github.com/cmullaparthi/ibrowse.git;protocol=git \
	   file://add-tetrapak.patch;apply=yes"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("ibrowse", "ibrowse-*", "ebin priv", "src include test *LICENSE", d)
}
