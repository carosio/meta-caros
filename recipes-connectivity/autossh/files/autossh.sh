#!/bin/sh
/usr/bin/autossh -M 0 -R ${port}:localhost:22 ${user}@${host} ${SSH_OPTIONS}
