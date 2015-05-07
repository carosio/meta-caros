#!/bin/sh -e
#
# Copyright (C) 2008-2011 Intel
#
# install.sh [device_name] [rootfs_name] [video_mode] [vga_mode]
#

PATH=/sbin:/bin:/usr/sbin:/usr/bin

boot_device="__TARGET_DEVICE__"

list_devices() {
    # copied from poky
    # Get a list of hard drives
    hdnamelist=""
    live_dev_name=${1%%/*}
    live_dev_name=${live_dev_name%%[0-9]*}

    echo "Searching for hard drives ..."

    for device in `ls /sys/block/`; do
	case $device in
	    loop*)
		# skip loop device
		;;
	    sr*)
		# skip CDROM device
		;;
	    ram*)
		# skip ram device
		;;
	    *)
		# skip the device LiveOS is on
		# Add valid hard drive name to the list
		if [ "$device" != "$live_dev_name" -a -e /dev/$device ]; then
		    hdnamelist="$hdnamelist $device"
		fi
		;;
	esac
    done

    for hdname in $hdnamelist; do
	# Display found hard drives and their basic info
	echo "-------------------------------"
	echo /dev/$hdname
	if [ -r /sys/block/$hdname/device/vendor ]; then
	    echo -n "VENDOR="
	    cat /sys/block/$hdname/device/vendor
	fi
	if [ -r /sys/block/$hdname/device/model ]; then
	    echo -n "MODEL="
	    cat /sys/block/$hdname/device/model
	fi
	if [ -r /sys/block/$hdname/device/uevent ]; then
	    echo -n "UEVENT="
	    cat /sys/block/$hdname/device/uevent
	fi
	echo
    done
}



### install scripts following ###

