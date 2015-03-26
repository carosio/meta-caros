#
# Copyright (C) 2012 Travelping GmbH
#
SUMMARY = "Image with TPLino components."
LICENSE = "MIT"

IMAGE_FEATURES += "splash ssh-server-dropbear ${TPLINO_IMAGE_FEATURES}"

inherit core-image-tplino core-image distro_features_check
