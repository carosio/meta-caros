DESCRIPTION = "BossDB: a sharded, caching, evented ORM"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=34e100fba47e6985909b431e17b02d10"

SRCREV = "ec01b43c28ff50c78dff28fd0f048f5d7ae978c7"
PR = "r1"

SRC_URI = "git://git@git.tpip.net/boss_db;protocol=ssh \
           file://remove_riak.patch;apply=yes"

S = "${WORKDIR}/git"

inherit tetrapak

DEPENDS_append = " erlang-erlydtl erlang-mochiweb erlang-aleppo erlang-bson erlang-epgsql erlang-erlmc erlang-medici erlang-mongodb erlang-mysql erlang-poolboy erlang-tiny-pq erlang-uuid "
RDEPENDS_${PN}_append = " erlang-erlydtl erlang-mochiweb erlang-aleppo erlang-bson erlang-erlmc erlang-medici erlang-poolboy erlang-tiny-pq erlang-uuid "


python () {
    erlang_def_package("boss-db", "boss_db*", "ebin priv", "src include .pc patches", d)
}
