DEPENDS += "rebar-native"
DEPENDS += "erlang erlang-native"
RDEPENDS_${PN} += "erlang"

erlang_libdir = "${libdir}/erlang/lib"

PACKAGES = "${PN}-dbg ${PN}-dev ${PN}"

ALLOW_EMPTY_${PN}-dev="1"
FILES_${PN}-dev="${erlang_libdir}/*/include \
  "

ALLOW_EMPTY_${PN}-dbg="1"
FILES_${PN}-dbg="${erlang_libdir}/*/lib/*/bin/.debug \
  ${erlang_libdir}/*/lib/*/lib/.debug \
  ${erlang_libdir}/*/lib/*/priv/lib/.debug \
  ${erlang_libdir}/*/lib/*/priv/obj/.debug \
  ${erlang_libdir}/*/lib/*/priv/bin/.debug \
  "

ALLOW_EMPTY_${PN}="1"
FILES_${PN}="${erlang_libdir}/*/ebin \
  ${erlang_libdir}/*/lib/*.so \
  "

# packages based on this class are copying some files
# together instead of "compiling" them (mainly deps)
INSANE_SKIP_${PN} = "already-stripped"

do_rebar_unpack_content() {
  bbnote "checking for contents.tar.gz"
  if [ -f ${WORKDIR}/contents.tar.gz ];
  then
    bbnote "contents.tar.gz found .. unpacking"
    tar xvf ${WORKDIR}/contents.tar.gz
  else
    bbnote "no contents.tar.gz found"
  fi
}

rebar_do_configure() {
  if [ -f "${S}/rebar.config" ];
  then
    install ${REBAR_FILES}/removeDeps.erl ${S}/.
    #remove dependencies
    escript removeDeps.erl rebar.config
  fi
  # TODO: check rather deps_dir and/or lib_dirs is already set and if it is set merge it
  echo >> rebar.config
  echo '{deps_dir, "${STAGING_DIR_TARGET}${erlang_libdir}"}.' >> rebar.config
  echo '{lib_dirs, ["${STAGING_DIR_TARGET}${erlang_libdir}"]}.' >> rebar.config
}

rebar_do_compile() {
  rebar compile
}

rebar_do_install() {
  #get Appname from compiled appfile
  APPNAME="$(cat ${S}/ebin/*.app |tr -d '\n "'|sed 's/^{application,\([^,]*\),.*$/\1/')"
  #get Appvervion from compiled appfile
  APPVERSION="$(cat ${S}/ebin/*.app |tr -d '\n "'|sed 's/^.*vsn,\([^}]*\)}.*$/\1/')"


  install -d ${D}${erlang_libdir}/$APPNAME-$APPVERSION

  if [ -d ${S}/ebin ];
  then
    bbnote "cp -rv ${S}/ebin ${D}${erlang_libdir}/$APPNAME-$APPVERSION"
    cp -rv ${S}/ebin ${D}${erlang_libdir}/$APPNAME-$APPVERSION
  fi

  if [ -d ${S}/include ];
  then
    bbnote "cp -rv ${S}/include ${D}${erlang_libdir}/$APPNAME-$APPVERSION"
    cp -rv ${S}/include ${D}${erlang_libdir}/$APPNAME-$APPVERSION
  fi

  if [ -d ${S}/erts-* ];
  then
    bbnote "cp -rv ${S}/erts-* ${D}${erlang_libdir}/$APPNAME-$APPVERSION"
    cp -rv ${S}/erts* ${D}${erlang_libdir}/$APPNAME-$APPVERSION
  fi

  if [ -d ${S}/lib ];
  then
    bbnote "cp -rv ${S}/lib ${D}${erlang_libdir}/$APPNAME-$APPVERSION"
    for x in $(find ${S}/lib -name "*.so");
    do
      cp -rv $x ${D}${erlang_libdir}/$APPNAME-$APPVERSION
    done;
  fi
}


EXPORT_FUNCTIONS do_compile do_install do_configure
addtask rebar_unpack_content after do_unpack before do_patch
