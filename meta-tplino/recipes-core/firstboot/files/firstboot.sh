#!/bin/sh

if [ -b /dev/sdb ]
then
    (echo n; echo p; echo ; echo ; echo ; echo w;)|fdisk /dev/sdb
    mkfs.ext4 /dev/sdb1
    mkdir -p /srv/data
    echo "/dev/sdb1  /srv/data  ext4  defaults  0  2" >> /etc/fstab
fi
mount -a
systemctl disable firstboot.service
