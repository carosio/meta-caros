require erlang-base.inc

DEPENDS += "openssl zlib ncurses"
export OE_LDFLAGS="-lz"

inherit autotools erlang

EXTRA_OECONF = '\
	     --with-termcap \
	     --without-java \
	     --with-ssl \
 	     --enable-threads \
	     --enable-smp-support \
	     --enable-shared-zlib \
	     --enable-dynamic-ssl-lib \
'

CACHED_CONFIGUREVARS += '\
	ac_cv_prog_emu_cc="$CC" \
	ac_cv_prog_RX_LD="${LD_FOR_TARGET}" \
	ac_cv_prog_JAVAC="" \
	ac_cv_prog_javac_ver_1_2=no \
	ac_cv_func_getaddrinfo=no \
	ac_cv_func_mmap_fixed_mapped=yes \
	ac_cv_path_WX_CONFIG_PATH=no \
	ac_cv_header_GL_gl_h=yes \
	ac_cv_header_OpenGL_gl_h=yes \
	ac_cv_lib_dlpi_dlpi_open=dont_try \
	erl_xcomp_linux_nptl=yes \
	erl_xcomp_getaddrinfo=yes \
	erl_xcomp_sysroot=${STAGING_DIR_HOST} \
'

EXTRA_OEMAKE = '-j1'

do_configure() {
    oe_runconf
}

do_compile() {
    oe_runmake noboot
}

do_install() {
    oe_runmake install INSTALL_PREFIX=${D}
}

python () {
    erlang_def_package("appmon", "appmon-*", "ebin priv", "src", d)
    erlang_def_package("asn1", "asn1-*", "ebin priv/lib/*so", "src asn1 examples", d)
    erlang_def_package("common-test", "common_test-*", "ebin priv", "src include", d)
    erlang_def_package("corba", "cosEvent-* cosEventDomain-* cosFileTransfer-* cosNotification-* cosProperty-* cosTime-* cosTransactions-* orber-*", "ebin priv", "src include COSS java_src examples", d)
    erlang_def_package("crypto", "crypto-*", "ebin priv/lib/*so", "src", d)
    erlang_def_package("debugger", "debugger-*", "ebin priv", "src", d)
    erlang_def_package("dialyzer", "dialyzer-*", "ebin", "src", d)
    erlang_def_package("diameter", "diameter-*", "ebin", "src include examples", d)
    erlang_def_package("edoc", "edoc-*", "ebin", "src priv include", d)
    erlang_def_package("eldap", "eldap-*", "ebin", "src asn1 include", d)
    erlang_def_package("erl-docgen", "erl_docgen-*", "ebin priv", "src", d)
    erlang_def_package("et", "et-*", "ebin", "src include examples", d)
    erlang_def_package("eunit", "eunit-*", "ebin", "src include examples", d)
    erlang_def_package("gs", "gs-*", "ebin priv", "src contribs examples", d)
    erlang_def_package("ic", "ic-*", "ebin", "src include examples", d)
    erlang_def_package("ic-java", "ic-java-*", "ebin", "src", d)
    erlang_def_package("inets", "inets-*", "ebin priv/lib/*so priv/bin", "src include examples", d)
    erlang_def_package("inviso", "inviso-*", "ebin", "src", d)
    erlang_def_package("manpages", "manpages-*", "ebin", "src", d)
    erlang_def_package("megaco", "megaco-*", "ebin priv", "src include examples", d)
    erlang_def_package("mnesia", "mnesia-* mnesia_session-*", "ebin", "src include examples", d)
    erlang_def_package("observer", "observer-*", "ebin priv", "src include examples", d)
    erlang_def_package("odbc", "odbc-*", "ebin", "src include", d)
    erlang_def_package("os-mon", "os_mon-*", "ebin priv mibs", "src include", d)
    erlang_def_package("parsetools", "parsetools-*", "ebin", "src include", d)
    erlang_def_package("percept", "percept-*", "ebin priv", "src", d)
    erlang_def_package("pman", "pman-*", "ebin priv", "src", d)
    erlang_def_package("public-key", "public_key-*", "ebin", "src asn1 include", d)
    erlang_def_package("reltool", "reltool-*", "ebin", "src examples", d)
    erlang_def_package("runtime-tools", "runtime_tools-*", "ebin priv/lib/*so", "src include examples", d)
    erlang_def_package("snmp", "snmp-* otp_mibs-*", "ebin priv", "bin mibs src include examples", d)
    erlang_def_package("ssh", "ssh-*", "ebin", "src include", d)
    erlang_def_package("ssl", "ssl-*", "ebin", "src include examples", d)
    erlang_def_package("syntax-tools", "syntax_tools-*", "ebin", "src examples", d)
    erlang_def_package("test-server", "test_server-*", "ebin", "src include", d)
    erlang_def_package("toolbar", "toolbar-*", "ebin", "src", d)
    erlang_def_package("tools", "tools-*", "ebin priv bin", "src examples", d)
    erlang_def_package("tv", "tv-*", "ebin priv", "src", d)
    erlang_def_package("typer", "typer-*", "ebin", "src", d)
    erlang_def_package("webtool", "webtool-*", "ebin priv", "src", d)
    erlang_def_package("wx", "wx-*", "ebin", "src include examples", d)
    erlang_def_package("xmerl", "xmerl-*", "ebin", "src include examples", d)
    erlang_def_package("jinterface", "jinterface-*", "ebin", "src", d)
}

PACKAGES_prepend = "erlang-ic-staticdev "

FILES_${PN} = '${erlbindir}/erlc \
               ${erlbindir}/run_erl \
               ${erlbindir}/to_erl \
               ${erlbindir}/epmd \
               ${erlbindir}/start_sasl.boot \
               ${erlbindir}/start_clean.boot \
               ${erlbindir}/start \
               ${erlbindir}/start_erl \
               ${erlbindir}/erl \
               ${erlbindir}/start.script \
               ${erlbindir}/escript \
               ${erlbindir}/start.boot \
               ${erldir}/erts-*/bin/beam \
	       ${erldir}/erts-*/bin/beam.smp \
               ${erldir}/erts-*/bin/beam.elib \
               ${erldir}/erts-*/bin/beam.elib.shared \
               ${erldir}/erts-*/bin/beam.shared \
               ${erldir}/erts-*/bin/child_setup \
               ${erldir}/erts-*/bin/child_setup.shared \
               ${erldir}/erts-*/bin/epmd \
               ${erldir}/erts-*/bin/dyn_erl \
               ${erldir}/erts-*/bin/erl \
               ${erldir}/erts-*/bin/erlc \
               ${erldir}/erts-*/bin/erlexec \
               ${erldir}/erts-*/bin/escript \
               ${erldir}/erts-*/bin/heart \
               ${erldir}/erts-*/bin/inet_gethost \
               ${erldir}/erts-*/bin/run_erl \
               ${erldir}/erts-*/bin/to_erl \
               ${erldir}/erts-*/bin/start \
               ${erldir}/erts-*/bin/beam.hybrid \
               ${erldir}/erts-*/bin/child_setup.hybrid \
               ${erldir}/releases \
               ${erllibdir}/erl_interface-*/bin/erl_call \
               ${erllibdir}/erts-*/ebin \
               ${erllibdir}/compiler-*/ebin \
               ${erllibdir}/kernel-*/ebin \
               ${erllibdir}/hipe-*/ebin \
               ${erllibdir}/stdlib-*/ebin \
               ${erllibdir}/sasl-*/ebin \
               /usr/bin/erlc \
               /usr/bin/run_erl \
               /usr/bin/erl_call \
               /usr/bin/erl \
               /usr/bin/to_erl \
               /usr/bin/start_embedded \
               /usr/bin/escript \
               /usr/bin/epmd'

FILES_${PN}-dev += '/usr/bin/typer \
                    /usr/bin/dialyzer \
                    /usr/bin/ct_run \
                    ${erldir}/usr/include \
                    ${erldir}/erts*/include \
                    ${erllibdir}/*/src \
                    ${erllibdir}/*/include \
                    ${erllibdir}/*/examples \
                    ${erllibdir}/*/mibs \
                    ${erllibdir}/hipe-*/icode \
                    ${erllibdir}/hipe-*/misc \
                    ${erllibdir}/hipe-*/main \
                    ${erllibdir}/hipe-*/util \
                    ${erllibdir}/hipe-*/cerl \
                    ${erllibdir}/hipe-*/flow'

FILES_${PN}-doc += '${erldir}/erts-*/doc ${erldir}/erts-*/man'

FILES_${PN}-staticdev += '${erldir}/usr/lib/*.a \
                          ${erldir}/erts*/lib/*.a \
                          ${erldir}/erts-*/lib/internal/*.a \
                          ${erllibdir}/erl_interface-*/lib/*.a'

FILES_${PN}-dbg += '${erlbindir}/.debug/ \
	            ${erldir}/erts*/bin/.debug \
	            ${erllibdir}/*/bin/.debug \
	            ${erllibdir}/*/priv/lib/.debug \
	            ${erllibdir}/*/priv/bin/.debug'

FILES_erlang-common-test += '${erldir}/erts-*/bin/ct_run ${erlbindir}/ct_run /usr/bin/ct_run'
FILES_erlang-dialyzer += '${erldir}/erts-*/bin/dialyzer ${erlbindir}/dialyzer /usr/bin/dialyzer'
FILES_erlang-typer += '${erldir}/erts-*/bin/typer ${erlbindir}/typer /usr/bin/typer'
FILES_erlang-webtool += '${erllibdir}/webtool-*/priv/bin/start_webtool /usr/bin/start_webtool'
FILES_erlang-diameter += '${erllibdir}/diameter-*/bin/diameterc /usr/bin/diameterc'

FILES_erlang-ic-staticdev += '${erllibdir}/ic-*/priv/lib/*.a'

# make install puts to much in the install direcory, do a bit of house keeping
do_install_append() {
    rm -rf \
	${D}/usr/bin/run_test \
	${D}${erldir}/bin/run_test \
	${D}${erldir}/erts-5.9.1/bin/*.src \
	${D}${erldir}/erts-*/src \
	${D}${erldir}/erts-5.9.1/lib/internal/README \
	${D}${erllibdir}/runtime_tools-*/priv/obj \
	${D}${erllibdir}/cosEvent-*/info \
	${D}${erllibdir}/cosEventDomain-*/info \
	${D}${erllibdir}/hipe-*/vsn.mk \
	${D}${erllibdir}/tools-*/c_src \
	${D}${erllibdir}/tools-*/emacs \
	${D}${erllibdir}/ic-*/c_src \
	${D}${erllibdir}/crypto-*/priv/obj \
	${D}${erllibdir}/asn1-*/c_src \
	${D}${erllibdir}/ic-*/priv \
	${D}${erldir}/misc \
	${D}${erldir}/Install

    EI_VSN=`sed -e"s|.*= *||g" ${S}/lib/erl_interface/vsn.mk`
    IC_VSN=`sed -e"s|.*= *||g" ${S}/lib/ic/vsn.mk`
    install -d -m0755 ${D}/usr/include ${D}/usr/lib
    ln -s ../lib/erlang/usr/include/driver_int.h				${D}/usr/include/driver_int.h
    ln -s ../lib/erlang/usr/include/erl_driver.h				${D}/usr/include/erl_driver.h
    ln -s ../lib/erlang/usr/include/erl_drv_nif.h				${D}/usr/include/erl_drv_nif.h
    ln -s ../lib/erlang/usr/include/erl_fixed_size_int_types.h			${D}/usr/include/erl_fixed_size_int_types.h
    ln -s ../lib/erlang/usr/include/erl_int_sizes_config.h			${D}/usr/include/erl_int_sizes_config.h
    ln -s ../lib/erlang/usr/include/erl_memory_trace_parser.h			${D}/usr/include/erl_memory_trace_parser.h
    ln -s ../lib/erlang/usr/include/erl_nif_api_funcs.h				${D}/usr/include/erl_nif_api_funcs.h
    ln -s ../lib/erlang/usr/include/erl_nif.h					${D}/usr/include/erl_nif.h
    ln -s ../lib/erlang/lib/erl_interface-${EI_VSN}/include/ei.h		${D}/usr/include/ei.h
    ln -s ../lib/erlang/lib/erl_interface-${EI_VSN}/include/ei_connect.h	${D}/usr/include/ei_connect.h
    ln -s ../lib/erlang/lib/erl_interface-${EI_VSN}/include/eicode.h		${D}/usr/include/eicode.h
    ln -s ../lib/erlang/lib/erl_interface-${EI_VSN}/include/erl_interface.h	${D}/usr/include/erl_interface.h
    ln -s ../lib/erlang/lib/ic-${IC_VSN}/include/ic.h				${D}/usr/include/ic.h
    ln -s erlang/usr/lib/liberts.a						${D}/usr/lib/liberts.a
    ln -s erlang/usr/lib/liberts_r.a						${D}/usr/lib/liberts_r.a
    ln -s erlang/lib/erl_interface-${EI_VSN}/lib/libei.a			${D}/usr/lib/libei.a
    ln -s erlang/lib/erl_interface-${EI_VSN}/lib/libei_st.a			${D}/usr/lib/libei_st.a
    ln -s erlang/lib/erl_interface-${EI_VSN}/lib/liberl_interface.a		${D}/usr/lib/liberl_interface.a
    ln -s erlang/lib/erl_interface-${EI_VSN}/lib/liberl_interface_st.a		${D}/usr/lib/liberl_interface_st.a
    ln -s erlang/lib/ic-${IC_VSN}/priv/lib/libic.a				${D}/usr/lib/libic.a
}
