#
# Copyright (C) 2012 Travelping GmbH
#
DESCRIPTION = "Image with TPLino components. It includes everything within \
core-image-tplinoo plus a native toolchain, application development and \
testing libraries, profiling and debug symbols."
LICENSE = "MIT"

inherit core-image-tplino core-image

IMAGE_FEATURES += "splash ssh-server-dropbear ${TPLINO_IMAGE_FEATURES} dev-pkgs"
