DESCRIPTION = "Chicago Boss web framework"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9445ab65d571cee0b2d6e3b4e35953cc"

SRCREV="f5a2576f72293e8ab06c621f641f08b7d232a29f"
PR = "r0"

SRC_URI = "git://git@git.tpip.net/boss.git;protocol=ssh"

S = "${WORKDIR}/git"

inherit tetrapak

DEPENDS_append = " erlang-boss-db erlang-tinymq erlang-tiny-pq erlang-erlydtl erlang-jaderl erlang-dynamic-compile erlang-gen-smtp erlang-misultin erlang-mochiweb erlang-simple-bridge erlang-mimetypes erlang-pmod-transform erlang-poolboy erlang-cowboy erlang-mochicow "
RDEPENDS_${PN}_append = " erlang-boss-db erlang-tinymq erlang-tiny-pq erlang-erlydtl erlang-jaderl erlang-dynamic-compile erlang-gen-smtp erlang-misultin erlang-mochiweb erlang-simple-bridge erlang-mimetypes erlang-pmod-transform erlang-poolboy erlang-cowboy erlang-mochicow "

python () {
    erlang_def_package("boss", "boss*", "ebin priv", "src include doc-src skel", d)
}
