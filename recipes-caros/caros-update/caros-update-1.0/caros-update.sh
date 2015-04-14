#!/bin/sh -e

PATH=/usr/sbin:/usr/bin:/sbin:/bin

opkg update
echo

cnt=$(opkg list-upgradable | wc -l)

if [ $cnt -eq 0 ]; then
    echo "No updates available."
    exit 0
fi

# Get user choice
while true; do
    echo -n "Found $cnt updates. Do you want to stage them for offline update? [y/n] "
    read answer
    if [ "$answer" = "y" -o "$answer" = "n" ]; then
         break
    fi
    echo "Please answer y or n"
done
if [ "$answer" != "y" ]; then
    exit 0
fi

# remove old/stale packages
rm -f #STATEDIR#/lib/caros-offline-update/*ipk

device=$(findmnt -n -u -o source / | awk -F '[][]' '{ print $1; }')
subvol=$(findmnt -n -u -o source / | awk -F '[][]' '{ print $2; }')

if [ "x$device" == "x" -o "x$subvol" == "x" ]; then
    echo "Unable to find current brtfs subvolume for snapshot, proceding without"
else
# create snapshot
    unshare --mount -- /bin/sh -c "\
        mount --make-rprivate /; \
        mount --make-private $device /mnt; \
        btrfs subvolume snapshot /mnt/$subvol /mnt/$subvol-backup-$(date +%FT%T); \
        umount /mnt
    "
fi

opkg upgrade --download-only --cache #STATEDIR#/lib/caros-offline-update || (
   echo "Update download failed."
   echo 0
)

rm -f /system-update
ln -sf #STATEDIR#/lib/caros-offline-update /system-update

echo
echo "Offline update is ready, please reboot to apply."
echo
exit 0
