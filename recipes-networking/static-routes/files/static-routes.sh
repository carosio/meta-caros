#!/bin/sh

grep -v '^#' $1 | while read LINE
do
  eval "ip route add $LINE"
done
