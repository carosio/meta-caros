SUMMARY = "Erlang UUID"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=e6a600fd5e1d9cbde2d983680233ad02"

SRCREV = "ae1856162130f03fbf89223879b44f8d8cb9f5e4"
PR = "r2"

SRC_URI = "git://github.com/carosio/erlang-uuid.git"

S = "${WORKDIR}/git"

inherit tetrapak

python () {
    erlang_def_package("uuid", "uuid*", "ebin priv", "README CONTRIBUTORS COPYING .gitignore rebar.config README.markdown src include test tetrapak", d)
}
