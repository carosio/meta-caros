TOOLCHAIN_TARGET_CAROSTASK ?= "task-core-standalone-caros-sdk-target task-core-standalone-caros-sdk-target-dbg"
TOOLCHAIN_TARGET_TASK = "${TOOLCHAIN_TARGET_CAROSTASK}"
TOOLCHAIN_OUTPUTNAME = "${SDK_NAME}-toolchain-caros-${DISTRO_VERSION}"
PROVIDES = "meta-toolchain-sdk"

TOOLCHAIN_NEED_CONFIGSITE_CACHE += "zlib"
