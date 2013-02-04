#
# Copyright (C) 2012 Travelping GmbH
#
DESCRIPTION = "Image with TPLino components."
LICENSE = "MIT"

inherit core-image-tplino core-image

IMAGE_FEATURES += "splash ssh-server-dropbear ${TPLINO_IMAGE_FEATURES}"


