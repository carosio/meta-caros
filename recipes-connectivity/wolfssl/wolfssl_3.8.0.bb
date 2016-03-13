SUMMARY = "wolfSSL Lightweight, Embedded SSL Library"
DESCRIPTION = "wolfSSL, formerly CyaSSL, is a lightweight SSL library written in C and \
               optimized for embedded and RTOS environments. It can be \
               Up to 20 times smaller than OpenSSL while still supporting \
               a full TLS 1.2 client and server."
HOMEPAGE = "http://www.wolfssl.com/yaSSL/Products-wolfssl.html"
BUGTRACKER = "http://github.com/wolfssl/wolfssl/issues"
SECTION = "libs"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PROVIDES += "cyassl"
RPROVIDES_${PN} = "cyassl"

SRC_URI = "http://www.wolfssl.com/${BP}.zip"

SRC_URI[md5sum] = "a73d90c5439adea97a5002a73b46ddeb"
SRC_URI[sha256sum] = "36369304dd107b2ae1360c91f3d0eea491802dcacb2155562652bf5257117016"

EXTRA_OECONF = "--enable-dtls \
	        --enable-ipv6 \
		--enable-aesgcm \
		--enable-aesccm \
		--enable-aesni \
		--enable-poly1305 \
		--enable-ecc \
		--enable-ecc25519 \
		--enable-chacha \
		--enable-supportedcurves \
		--enable-dh \
		--enable-psk \
		--disable-des3 \
		--disable-arc4 \
		"

inherit autotools

PACKAGES =+ "${PN}-cyassl-dev"

FILES_${PN}-cyassl-dev = "/usr/include/cyassl"