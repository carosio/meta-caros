DEPENDS += "rebar-native"

do_update_rebar() {
    find ${S} -name rebar -exec ln -sf ${STAGING_DIR_NATIVE}${bindir_native}/rebar {} \;
}

addtask update_rebar after do_patch before do_configure