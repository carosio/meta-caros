#
# Copyright (C) 2012 Travelping GmbH
#
DESCRIPTION = "Image with TPLino components. It includes everything within \
core-image-tplinoo plus meta-toolchain, development headers and libraries to \
form a standalone SDK."
LICENSE = "MIT"

inherit core-image-tplino core-image

IMAGE_FEATURES += "splash ssh-server-dropbear ${TPLINO_IMAGE_FEATURES} ${TPLINO_IMAGE_SDK_FEATURES} dev-pkgs tools-sdk"
EXTRA_IMAGE_FEATURES += "tools-debug tools-profile debug-tweaks"
