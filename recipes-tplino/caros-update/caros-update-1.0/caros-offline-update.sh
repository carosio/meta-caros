#!/bin/sh

set -x

PATH=/usr/sbin:/usr/bin:/sbin:/bin

do_log() {
    echo > /dev/console
    echo "caros-update: $1" > /dev/console
    systemd-cat echo "$1"
}

update_dir=$(readlink -fn /system-update)

# do this as soon as possible to avoid a loop if this tool fails
rm -f /system-update

if [ ! -d ${update_dir} ]; then
    do_log "'${update_dir}' not found, terminating"
    sleep 30
    exit 1
fi

# apply the update
opkg upgrade --autoremove --cache ${update_dir}

# remove all cached package
rm -f ${update_dir}/*ipk

#
# any post upgrade action that might be required could be hooked in here....
#
do_log "Update succeeded"

dbus-send --system --type=method_call --print-reply=literal \
          --dest=org.freedesktop.systemd1 \
          /org/freedesktop/systemd1 org.freedesktop.systemd1.Manager.Reboot

# should not be reached
exit 1
