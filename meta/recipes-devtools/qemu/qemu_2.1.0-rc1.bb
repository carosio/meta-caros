require qemu.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=441c28d2cf86e15a37fa47e15a72fbac \
                    file://COPYING.LIB;endline=24;md5=c04def7ae38850e7d3ef548588159913"

SRC_URI += "file://qemu-enlarge-env-entry-size.patch \
            file://Qemu-Arm-versatilepb-Add-memory-size-checking.patch \
	    file://improve-vmdk-streaming.patch"

SRC_URI_prepend = "http://wiki.qemu.org/download/qemu-${PV}.tar.bz2"
SRC_URI[md5sum] = "15432d8be2a420ded69b31f6345fb162"
SRC_URI[sha256sum] = "ebac4b3ebea59e3ebbc6f1674a60285c608ef9c0f19715ea592e162c682aee6b"

COMPATIBLE_HOST_class-target_mips64 = "null"
