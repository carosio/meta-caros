SUMMARY = "BossDB: a sharded, caching, evented ORM"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9445ab65d571cee0b2d6e3b4e35953cc"

PR = "r2"

SRC_URI = "https://github.com/ChicagoBoss/boss_db/archive/v${PV}.tar.gz;dowloadfilename=${P}.tar.gz \
           file://config.ini"
SRC_URI[md5sum] = "8b3be547ff2098bee6e38b605c191940"
SRC_URI[sha256sum] = "ee67842af25c312f419923196ab9bee93aef308e30eaa7d170c5306a6cdc8128"

S = "${WORKDIR}/boss_db-${PV}"

DEPENDS = "erlang-lager erlang-erlando erlang-erlydtl erlang-mochiweb erlang-aleppo erlang-bson erlang-epgsql erlang-erlmc erlang-medici erlang-mongodb erlang-mysql erlang-poolboy erlang-tiny-pq erlang-uuid erlang-ets-cache erlang-dh-date"
RDEPENDS_${PN} = "erlang-erlydtl erlang-mochiweb erlang-aleppo erlang-bson erlang-erlmc erlang-medici erlang-poolboy erlang-tiny-pq erlang-uuid erlang-ets-cache erlang-dh-date"

inherit tetrapak

TETRAPAK_OPTS += "-o build.version ${PV}"

addtask do_prepare_compile after do_patch before do_compile

do_prepare_compile() {
    # remove dependency on proper and fix test support
    sed -i -e"s/.*proper.hrl.*//g" \
    	   -e"s/%-ifdef/-ifdef/g" \
	   -e"s/%-endif/-endif/g" ${S}/src/boss_record.erl
    rm ${S}/src/prop_runner.erl \
       ${S}/src/boss_db_test_app.erl \
       ${S}/src/boss_db_test.erl

    # remove riak support
    rm ${S}/src/db_adapters/boss_db_adapter_riak.erl

    # remove dynamodb support
    rm ${S}/src/db_adapters/boss_db_adapter_dynamodb.erl

    # remove redis support
    rm ${S}/src/cache_adapters/boss_cache_adapter_redis.erl

    # add tetrapak
    mkdir -p ${S}/tetrapak
    cp ${WORKDIR}/config.ini ${S}/tetrapak
}

python () {
    erlang_def_package("boss-db", "boss_db*", "ebin priv", "src include test build dialyzer.sh make-plt.sh", d)
}
