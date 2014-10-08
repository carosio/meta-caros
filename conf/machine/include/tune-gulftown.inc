DEFAULTTUNE ?= "gulftown-64"

require conf/machine/include/tune-core2.inc

# Extra tune features
TUNEVALID[gulftown] = "Enable gulftown specific processor optimizations"
TUNE_CCARGS += "${@bb.utils.contains("TUNE_FEATURES", "gulftown", "-march=corei7 -maes -mfpmath=sse -mpclmul -mpopcnt -mcx16 -msahf -mtls-dialect=gnu2", "", d)}"

# Extra tune selections
AVAILTUNES += "gulftown"
TUNE_FEATURES_tune-gulftown = "${TUNE_FEATURES_tune-x86} gulftown"
BASE_LIB_tune-gulftown = "lib"
TUNE_PKGARCH_tune-gulftown = "gulftown"
PACKAGE_EXTRA_ARCHS_tune-gulftown = "${PACKAGE_EXTRA_ARCHS_tune-x86} i386 i486 i586 i686 core2 gulftown"

AVAILTUNES += "gulftown-64"
TUNE_FEATURES_tune-gulftown-64 = "${TUNE_FEATURES_tune-x86-64} gulftown"
BASE_LIB_tune-gulftown-64 = "lib64"
TUNE_PKGARCH_tune-gulftown-64 = "gulftown_64"
PACKAGE_EXTRA_ARCHS_tune-gulftown-64 = "${PACKAGE_EXTRA_ARCHS_tune-x86-64} core2_64 gulftown_64"

AVAILTUNES += "gulftown-64-x32"
TUNE_FEATURES_tune-gulftown-64-x32 = "${TUNE_FEATURES_tune-x86-64-x32} gulftown"
BASE_LIB_tune-gulftown-64-x32 = "libx32"
TUNE_PKGARCH_tune-gulftown-64-x32 = "gulftown_64_x32"
PACKAGE_EXTRA_ARCHS_tune-gulftown-64-x32 = "${PACKAGE_EXTRA_ARCHS_tune-x86-64-x32} core2_64_x32 gulftown_64_x32"

