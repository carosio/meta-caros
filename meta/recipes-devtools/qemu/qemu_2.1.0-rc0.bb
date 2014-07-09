require qemu.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=441c28d2cf86e15a37fa47e15a72fbac \
                    file://COPYING.LIB;endline=24;md5=c04def7ae38850e7d3ef548588159913"

SRC_URI = "http://wiki.qemu.org/download/qemu-${PV}.tar.bz2 \
	   file://fix-vmdk-streaming.patch"
SRC_URI[md5sum] = "b8e7af12112d4859ea30196975b1fd57"
SRC_URI[sha256sum] = "6065f47b54f6fb5a3fa6a2b4b021ac49bc70314ee4638eb6929d52f8d89b1882"
