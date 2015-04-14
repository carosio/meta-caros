#
# Copyright (C) 2012 Travelping GmbH
#
DESCRIPTION = "Image with CarOS components. It includes everything within \
core-image-caros plus meta-toolchain, development headers and libraries to \
form a standalone SDK."
LICENSE = "MIT"

inherit core-image-caros core-image

IMAGE_FEATURES += "splash ssh-server-dropbear ${CAROS_IMAGE_FEATURES} ${CAROS_IMAGE_SDK_FEATURES} dev-pkgs tools-sdk"
EXTRA_IMAGE_FEATURES += "tools-debug tools-profile debug-tweaks"
