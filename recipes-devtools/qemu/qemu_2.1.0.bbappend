FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append_class-native = "\
    file://improve-vmdk-streaming.patch \
    "
