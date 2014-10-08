#
# Copyright (C) 2012 Travelping GmbH
#
DESCRIPTION = "Image with TPLino components."
LICENSE = "MIT"

include recipes-vmware/ovf/vmware-ovf.inc
inherit core-image-tplino core-image

IMAGE_FEATURES += "splash ssh-server-dropbear ${TPLINO_IMAGE_FEATURES}"


