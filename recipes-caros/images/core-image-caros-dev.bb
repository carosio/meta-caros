#
# Copyright (C) 2012 Travelping GmbH
#
DESCRIPTION = "Image with CarOS components. It includes everything within \
core-image-caros plus a native toolchain, application development and \
testing libraries, profiling and debug symbols."
LICENSE = "MIT"

inherit core-image-caros core-image

IMAGE_FEATURES += "splash ssh-server-dropbear ${CAROS_IMAGE_FEATURES} dev-pkgs"
