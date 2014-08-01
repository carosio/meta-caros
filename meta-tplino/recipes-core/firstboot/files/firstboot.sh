#!/bin/sh

if [ -b /dev/sdb -a ! -b /dev/sdb1 ]
then
    # create a new ext4 partition on /dev/sdb using fdisk
    (echo n; echo p; echo ; echo ; echo ; echo w;)|fdisk /dev/sdb
    mkfs.ext4 /dev/sdb1
    # mount new partition as /srv/data
    mkdir -p /srv/data
    echo "/dev/sdb1  /srv/data  ext4  defaults  0  2" >> /etc/fstab
    mount -a
    # disable service
    systemctl disable firstboot.service
else
    echo "Nothing to do."
    systemctl disable firstboot.service
fi
