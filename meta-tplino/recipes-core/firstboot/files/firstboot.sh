#!/bin/sh

(echo n; echo p; echo ; echo ; echo ; echo w;)|fdisk /dev/sdb
mkfs.ext4 /dev/sdb1
mkdir -p /srv/data
echo "/dev/sdb1  /srv/data  ext4  defaults  0  2" >> /etc/fstab
mount -a
systemctl disable firstboot.service
