require bind.inc

SRC_URI = "ftp://ftp.isc.org/isc/bind9/${PV}/${BPN}-${PV}.tar.gz"

SRC_URI[md5sum] = "7baa8359f0773e04f63d7e694db1909c"  
SRC_URI[sha256sum] = "e45c08217eb56eb35aa39f2bb2f6fa77ee626b4dcef165a1bf0f522e252fd6bc" 

inherit native
