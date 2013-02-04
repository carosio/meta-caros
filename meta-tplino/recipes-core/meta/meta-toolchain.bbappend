TOOLCHAIN_TARGET_TPLINOTASK ?= "task-core-standalone-tplino-sdk-target task-core-standalone-tplino-sdk-target-dbg"
TOOLCHAIN_TARGET_TASK = "${TOOLCHAIN_TARGET_TPLINOTASK}"
TOOLCHAIN_OUTPUTNAME = "${SDK_NAME}-toolchain-tplino-${DISTRO_VERSION}"
PROVIDES = "meta-toolchain-sdk"

TOOLCHAIN_NEED_CONFIGSITE_CACHE += "zlib"
