#
# Copyright (C) 2015 Travelping GmbH
#
SUMMARY = "Image with CarOS components."
LICENSE = "MIT"

inherit core-image-caros core-image distro_features_check extrausers

IMAGE_FEATURES += "splash ssh-server-openssh ${CAROS_IMAGE_FEATURES}"
IMAGE_INSTALL += "util-linux-setterm"
IMAGE_INSTALL += "parted btrfs-tools e2fsprogs-mke2fs e2fsprogs-resize2fs e2fsprogs-tune2fs"
IMAGE_INSTALL += "caros-core"

# having at least 4k blocks allows conversion from ext3 to btrfs
EXTRA_IMAGECMD_ext3 += "-b 4096"

# set root password to 'caros'
#
# compute new value with: perl -e 'print crypt("secret","\$6\$pHNG7CcLpB/\$") . "\n"'
# dollar ($) signs need backspace encoding
#
EXTRA_USERS_PARAMS = "usermod -p '\$6\$pHNG7CcLpB/\$Ema9IqIVLbBX.RbaYeVULVXPsq67amN5QEgjLF9zKWHtx2UhAgopVI.TcgP8WJw58G1LczqWlPIe.39/c2CNL.' root; \
                        useradd -m -s /bin/zsh caros; usermod -p '\$6\$pHNG7CcLpB/\$Ema9IqIVLbBX.RbaYeVULVXPsq67amN5QEgjLF9zKWHtx2UhAgopVI.TcgP8WJw58G1LczqWlPIe.39/c2CNL.' caros"
