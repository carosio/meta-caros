#
# Copyright (C) 2015 Travelping GmbH
#
SUMMARY = "Image with CarOS components."
LICENSE = "MIT"

IMAGE_FEATURES += "splash ssh-server-openssh ${CAROS_IMAGE_FEATURES}"

inherit core-image-caros core-image distro_features_check extrausers

# set root password to 'caros'
#
# compute new value with: perl -e 'print crypt("scg","\$6\$pHNG7CcLpB/\$") . "\n"'
# dollar ($) signs need backspace encoding
#
EXTRA_USERS_PARAMS = "usermod -p '\$6\$pHNG7CcLpB/\$Ema9IqIVLbBX.RbaYeVULVXPsq67amN5QEgjLF9zKWHtx2UhAgopVI.TcgP8WJw58G1LczqWlPIe.39/c2CNL.' root;"
