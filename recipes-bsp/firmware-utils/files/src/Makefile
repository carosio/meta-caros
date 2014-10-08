#
# mostly taken from OpenWRT:
#
# Copyright (C) 2006-2012 OpenWrt.org
#
# This is free software, licensed under the GNU General Public License v2.
# See /LICENSE for more information.
#

define cc
	$(CC) $(CFLAGS) -I./include -include endian.h -o build/$(firstword $(1)) $(foreach src,$(1),$(src).c) $(LDFLAGS) $(2)
endef

all:
	mkdir -p build
	$(call cc,addpattern)
	$(call cc,trx)
	$(call cc,motorola-bin)
	$(call cc,dgfirmware)
	$(call cc,mkdir615h1 md5)
	$(call cc,trx2usr)
	$(call cc,ptgen)
	$(call cc,airlink)
	$(call cc,srec2bin)
	$(call cc,mkmylofw)
	$(call cc,mkcsysimg)
	$(call cc,mkzynfw)
	$(call cc,lzma2eva,-lz)
	$(call cc,mkcasfw)
	$(call cc,mkfwimage,-lz)
	$(call cc,mkfwimage2,-lz)
	$(call cc,imagetag imagetag_cmdline)
	$(call cc,add_header)
	$(call cc,makeamitbin)
	$(call cc,encode_crc)
	$(call cc,nand_ecc)
	$(call cc,mkplanexfw sha1)
	$(call cc,mktplinkfw md5)
	$(call cc,pc1crypt)
	$(call cc,osbridge-crc)
	$(call cc,wrt400n cyg_crc32)
	$(call cc,wndr3700)
	$(call cc,mkdniimg)
	$(call cc,mktitanimg)
	$(call cc,mkchkimg)
	$(call cc,mkzcfw cyg_crc32)
	$(call cc,spw303v)
	$(call cc,trx2edips)
	$(call cc,xorimage)
	$(call cc,buffalo-enc buffalo-lib, -Wall)
	$(call cc,buffalo-tag buffalo-lib, -Wall)
	$(call cc,buffalo-tftp buffalo-lib, -Wall)
	$(call cc,mkwrgimg md5, -Wall)
	$(call cc,mkedimaximg)
	$(call cc,mkbrncmdline)
	$(call cc,mkbrnimg)
	$(call cc,mkdapimg)
	$(call cc, mkcameofw, -Wall)
	$(call cc,seama md5)
	$(call cc,fix-u-media-header cyg_crc32,-Wall)
	$(call cc,hcsmakeimage bcmalgo)