
# CarOS install script

# wait for system to settle
sleep 1
echo -e "\n\n"

if [ -n "$boot_device" -a ! -r /sys/block/${boot_device}/dev ]; then
    echo "WARNING: The specified target device /dev/${boot_device} could not be found!"
    echo "Press ENTER to proceed."
    boot_device=""
    read
fi

if [ -z "${boot_device}" ] ; then
    list_devices
    echo -n "Enter target device name: "
    read user_input
    boot_device=${user_input#/dev/}
fi

if [ -r /sys/block/${boot_device}/dev ]; then
    echo "Installing image on /dev/${boot_device} ..."
else
    echo "Invalid target device: /dev/${boot_device}. Installation aborted."
    exit 1
fi


#
# The udev automounter can cause pain here, kill it
#
rm -f /etc/udev/rules.d/automount.rules
rm -f /etc/udev/scripts/mount*

#
# Unmount anything the automounter had mounted
#
umount /dev/${boot_device}* 2> /dev/null || /bin/true

if [ ! -b /dev/loop0 ] ; then
    mknod /dev/loop0 b 7 0
fi

mkdir -p /tmp
if [ ! -L /etc/mtab ]; then
	cat /proc/mounts > /etc/mtab
fi

echo "*****************"

# Get user choice
while true; do
    echo    "This will install a new release, all existing data will be DESTROYED."
    echo -n "Proceed with installation? [y/n] "
    read answer
    if [ "$answer" = "y" -o "$answer" = "n" ]; then
        break
    fi
    echo "Please answer y or n"
done
if [ "$answer" = "n" ]; then
    echo "Installation aborted by user."
    exit 1
fi

echo "Deleting partition table on /dev/${boot_device} ..."
dd if=/dev/zero of=/dev/${boot_device} bs=512 count=2

echo "Creating new GPT label and partitions on /dev/${boot_device} ..."
parted -s /dev/sda -- mklabel gpt \
       mkpart BIOS 20KiB 1MiB \
       mkpart EFI 1MiB 200MiB \
       mkpart System 200MiB 100% \
       set 1 bios_grub on \
       set 2 boot on

# wait for devices
udevadm settle

biosfs=/dev/${boot_device}1
efifs=/dev/${boot_device}2
rootfs=/dev/${boot_device}3

echo "Formatting $efifs to FAT32..."
mkfs.vfat -F 32 ${efifs}

echo "Formatting $rootfs to btrfs..."
mkfs.btrfs -f ${rootfs}

mkdir /tgt_root
mkdir /src_root

# Handling of the target root partition
mount $rootfs /tgt_root
btrfs subvolume create /tgt_root/@

# Handling of the target root subvolume
mount -o rw,loop,noatime,nodiratime /run/media/$1/$2 /src_root
echo "Copying rootfs files..."
cp -a /src_root/* /tgt_root/@
if [ -d /tgt_root/@/etc/udev/ ] ; then
    echo "/dev/${boot_device}" >> /tgt_root/@/etc/udev/mount.blacklist
fi
if [ ! -s /tgt_root/@/etc/machine-id ] ; then
    echo "Initializing permanent machine-id..."
    rm -f /tgt_root/@/etc/machine-id
    mount -o bind /dev /tgt_root/@/dev
    mount -o bind /proc /tgt_root/@/proc
    chroot /tgt_root/@ /bin/systemd-machine-id-setup
    umount /tgt_root/@/proc
    umount /tgt_root/@/dev
fi

# Handling of the target boot partition
echo "Preparing boot partition..."
if [ -f /etc/grub.d/42_caros ] ; then
    echo "Preparing custom grub2 menu..."
    GRUBCFG="/tgt_root/boot/grub/grub.cfg"
    mkdir -p $(dirname $GRUBCFG)
    cp /etc/grub.d/42_caros $GRUBCFG
    sed -i "s@__ROOTFS__@$rootfs $rootwait@g" $GRUBCFG
    sed -i "s/__VIDEO_MODE__/$3/g" $GRUBCFG
    sed -i "s/__VGA_MODE__/$4/g" $GRUBCFG
    sed -i "s/__CONSOLE__/$5/g" $GRUBCFG
    sed -i "/#/d" $GRUBCFG
    sed -i "/exec tail/d" $GRUBCFG
    chmod 0444 $GRUBCFG
fi
grub-install --root-directory=/tgt_root /dev/${boot_device}
echo "(hd0) /dev/${boot_device}" > /tgt_root/boot/grub/device.map

umount /tgt_root
umount /src_root

