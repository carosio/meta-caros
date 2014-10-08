#overwrite the default profile tools, remove UI components

PROFILETOOLS = "\
    oprofile \
    powertop \
    lttng-control"

RRECOMMENDS_${PN} = "\
    perf \
    trace-cmd \
    kernel-module-oprofile \
    blktrace \
    "

RDEPENDS_${PN} = "\
    ${PROFILETOOLS} \
    ${LTTNGUST} \
    ${SYSTEMTAP} \
    ${VALGRIND} \
    "
