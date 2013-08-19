#!/bin/sh
/usr/sbin/dropbearkey -t rsa -f /etc/dropbear/dropbear_rsa_host_key | /bin/grep "^ssh-rsa " > /home/root/.ssh/dropbear.pub
