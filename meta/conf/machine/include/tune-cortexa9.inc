DEFAULTTUNE ?= "armv7a-neon"

require conf/machine/include/arm/arch-armv7a.inc

TUNEVALID[cortexa9] = "Enable Cortex-A9 specific processor optimizations"
TUNE_CCARGS += "${@bb.utils.contains("TUNE_FEATURES", "cortexa9", "-mtune=cortex-a9", "", d)}"

# Little Endian base configs
AVAILTUNES += "cortexa9 cortexa9t cortexa9-neon cortexa9t-neon"
ARMPKGARCH_tune-cortexa9 = "cortexa9"
ARMPKGARCH_tune-cortexa9t = "cortexa9"
ARMPKGARCH_tune-cortexa9-neon = "cortexa9"
ARMPKGARCH_tune-cortexa9t-neon = "cortexa9"
TUNE_FEATURES_tune-cortexa9 = "${TUNE_FEATURES_tune-armv7a} cortexa9"
TUNE_FEATURES_tune-cortexa9t = "${TUNE_FEATURES_tune-armv7at} cortexa9"
TUNE_FEATURES_tune-cortexa9-neon = "${TUNE_FEATURES_tune-armv7a-neon} cortexa9"
TUNE_FEATURES_tune-cortexa9t-neon = "${TUNE_FEATURES_tune-armv7at-neon} cortexa9"
PACKAGE_EXTRA_ARCHS_tune-cortexa9 = "${PACKAGE_EXTRA_ARCHS_tune-armv7a} cortexa9-vfp"
PACKAGE_EXTRA_ARCHS_tune-cortexa9t = "${PACKAGE_EXTRA_ARCHS_tune-armv7at} cortexa9-vfp cortexa9t2-vfp"
PACKAGE_EXTRA_ARCHS_tune-cortexa9-neon = "${PACKAGE_EXTRA_ARCHS_tune-armv7a-neon} cortexa9-vfp cortexa9-vfp-neon"
PACKAGE_EXTRA_ARCHS_tune-cortexa9t-neon = "${PACKAGE_EXTRA_ARCHS_tune-armv7at-neon} cortexa9-vfp cortexa9-vfp-neon cortexa9t2-vfp cortexa9t2-vfp-neon"

# VFP Tunes
AVAILTUNES += "cortexa9hf cortexa9thf cortexa9hf-neon cortexa9thf-neon"
ARMPKGARCH_tune-cortexa9hf = "cortexa9"
ARMPKGARCH_tune-cortexa9thf = "cortexa9"
ARMPKGARCH_tune-cortexa9hf-neon = "cortexa9"
ARMPKGARCH_tune-cortexa9thf-neon = "cortexa9"
TUNE_FEATURES_tune-cortexa9hf ?= "${TUNE_FEATURES_tune-armv7ahf} cortexa9"
TUNE_FEATURES_tune-cortexa9thf ?= "${TUNE_FEATURES_tune-armv7athf} cortexa9"
TUNE_FEATURES_tune-cortexa9hf-neon ?= "${TUNE_FEATURES_tune-armv7ahf-neon} cortexa9"
TUNE_FEATURES_tune-cortexa9thf-neon ?= "${TUNE_FEATURES_tune-armv7ahf-neon} cortexa9"
PACKAGE_EXTRA_ARCHS_tune-cortexa9hf = "${PACKAGE_EXTRA_ARCHS_tune-armv7ahf} cortexa9hf-vfp"
PACKAGE_EXTRA_ARCHS_tune-cortexa9thf = "${PACKAGE_EXTRA_ARCHS_tune-armv7athf} cortexa9hf-vfp cortexa9t2hf-vfp"
PACKAGE_EXTRA_ARCHS_tune-cortexa9hf-neon = "${PACKAGE_EXTRA_ARCHS_tune-armv7ahf-neon} cortexa9hf-vfp cortexa9hf-vfp-neon"
PACKAGE_EXTRA_ARCHS_tune-cortexa9thf-neon = "${PACKAGE_EXTRA_ARCHS_tune-armv7athf-neon} cortexa9hf-vfp cortexa9hf-vfp-neon cortexa9t2-vfp cortexa9t2hf-vfp-neon"