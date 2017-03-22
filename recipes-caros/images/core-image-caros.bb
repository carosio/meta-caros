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
IMAGE_INSTALL += "carosadm"
IMAGE_INSTALL += "packagegroup-caros-ansible-deps"

# having at least 4k blocks allows conversion from ext3 to btrfs
EXTRA_IMAGECMD_ext3 += "-b 4096"

#
# compute new password hashes: perl -e 'print crypt("secret","\$6\$pHNG7CcLpB/\$") . "\n"'
# dollar ($) signs need backspace encoding
#
# set root password to 'caros'
EXTRA_USERS_PARAMS = "usermod -p '\$6\$pHNG7CcLpB/\$Ema9IqIVLbBX.RbaYeVULVXPsq67amN5QEgjLF9zKWHtx2UhAgopVI.TcgP8WJw58G1LczqWlPIe.39/c2CNL.' root;"

# are you looking for the place `carosadm` user is created?
# check meta-caros/recipes-caros/carosadm/*.bb

EXTRA_USERS_PARAMS += "userdel games;"
EXTRA_USERS_PARAMS += "userdel gnats;"
EXTRA_USERS_PARAMS += "userdel irc;"
EXTRA_USERS_PARAMS += "userdel list;"
EXTRA_USERS_PARAMS += "userdel lp;"
EXTRA_USERS_PARAMS += "userdel news;"
EXTRA_USERS_PARAMS += "userdel proxy;"
EXTRA_USERS_PARAMS += "userdel sync;"
EXTRA_USERS_PARAMS += "userdel uucp;"
EXTRA_USERS_PARAMS += "userdel www-data;"

EXTRA_USERS_PARAMS += "usermod -s /sbin/nologin bin;"
EXTRA_USERS_PARAMS += "usermod -s /sbin/nologin daemon;"
EXTRA_USERS_PARAMS += "usermod -s /sbin/nologin mail;"
EXTRA_USERS_PARAMS += "usermod -s /sbin/nologin man;"
EXTRA_USERS_PARAMS += "usermod -s /sbin/nologin nobody;"
EXTRA_USERS_PARAMS += "usermod -s /sbin/nologin sys;"

