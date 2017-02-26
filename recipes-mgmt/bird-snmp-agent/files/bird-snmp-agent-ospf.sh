#!/bin/sh
export PYTHONPATH=@LIBEXEC@
exec env python @LIBEXEC@/bird_ospf.py
