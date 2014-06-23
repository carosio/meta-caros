require tetrapak-native.inc
SRC_URI = "git://git@git.tpip.net/tetrapak.git;protocol=ssh \
           file://plugin_auto_search_false.patch;apply=yes"
SRCREV="130768f925e02b5429324c1eb966bebf221341db"

PR = "${INC_PR}.2"
