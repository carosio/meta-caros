SUMMARY = "Chicago Boss web framework, now serving three flavors of Comet"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9445ab65d571cee0b2d6e3b4e35953cc"

PR = "r1"

SRCREV_ORIG = "${PV}"
SRCREV = "v${SRCREV_ORIG}"

SRC_URI = "https://github.com/ChicagoBoss/ChicagoBoss/archive/${SRCREV}.tar.gz;downloadfilename=ChicagoBoss-${SRCREV_ORIG}.tar.gz \
           file://config.ini"
SRC_URI[md5sum] = "9b6a417f68c3b904c2659b10888c7b39"
SRC_URI[sha256sum] = "d9dd51b81826c3da08d9d033b01a28c5e24cd0a28ca691cb499186b9392e37c3"

S = "${WORKDIR}/ChicagoBoss-${SRCREV_ORIG}"

TETRAPAK_OPTS += "-o build.version ${PV}"
TETRAPAK_OPTS += "-o package.maintainer 'Travelping GmbH <info@travelping.com>'"
TETRAPAK_OPTS += "-o package.exclude '^\.gitignore|^\.travis\.yml|^CHANGELOG\.md|^CODING_STANDARDS\.md|^ct|^deps|^dialyzer\.sh|^doc-src|^make-plt\.sh|^Makefile|^mix\.exs|^README_DATABASE\.md|^README_ELIXIR\.md|^README_FILTERS\.md|^README_LFE\.md|^README_TESTS\.md|^README_UPGRADE\.md|^rebar|^rebar\.cmd|^rebar\.config|^sample_configs|^skel|^skel\.template|^travis-dialyzer\.sh|^windows-make\.bat'"

inherit tetrapak

DEPENDS += "erlang-lager erlang-boss-db erlang-tinymq erlang-erlydtl erlang-jaderl erlang-dynamic-compile erlang-gen-smtp erlang-misultin erlang-mochiweb erlang-mimetypes erlang-pmod-transform erlang-simple-bridge erlang-lfe"
RDEPENDS_${PN} += "erlang-boss-db erlang-tinymq erlang-erlydtl erlang-jaderl erlang-dynamic-compile erlang-gen-smtp erlang-misultin erlang-mochiweb erlang-mimetypes erlang-pmod-transform erlang-simple-bridge"

addtask do_prepare_compile after do_patch before do_compile

do_prepare_compile() {
    # remove dependency on proper and fix test support
    sed -i -e"s/.*proper.hrl.*//g" \
           -e"s/%-ifdef/-ifdef/g" \
       -e"s/%-endif/-endif/g" ${S}/src/boss/boss_controller_compiler.erl

    # add tetrapak
    mkdir -p ${S}/tetrapak
    cp ${WORKDIR}/config.ini ${S}/tetrapak
}

python () {
    erlang_def_package("boss", "boss*", "bin ebin priv", "include LICENSE README.md src test", d)
}
