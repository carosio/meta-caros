require go-common.inc

GO_TAG = "1_6"

SRC_URI = "${SRC_URI_PREFIX}/go${PV}.src.tar.gz;name=go_${GO_TAG};subdir=go-${PV}"
SRC_URI[go_1_6.md5sum] = "e67833ea37fbc002fbe38efe6c1bcd98"
SRC_URI[go_1_6.sha256sum] = "a96cce8ce43a9bf9b2a4c7d470bc7ee0cb00410da815980681c8353218dcf146"

SRC_URI += "\
        file://Fix-ccache-compilation-issue.patch \
        file://fix-cross-compilation.patch \
        "
