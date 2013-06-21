require erlang.inc

PR = "r4"
ALLOW_EMPTY_${PN} = "1"

SRC_URI = "http://www.erlang.org/download/otp_src_${PV}.tar.gz"

SRC_URI[md5sum] = "f12d00f6e62b36ad027d6c0c08905fad"
SRC_URI[sha256sum] = "f94f7de7328af3c0cdc42089c1a4ecd03bf98ec680f47eb5e6cddc50261cabde"

RCONFLICTS_${PN} = "erlang16"
