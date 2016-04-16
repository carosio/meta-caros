require go.inc

GO_TAG = "1_5"

SRC_URI = "${SRC_URI_PREFIX}/go${PV}.src.tar.gz;name=go_${GO_TAG};subdir=go-${PV}"
SRC_URI[go_1_5.md5sum] = "80a0eac7ab750b01b3f7096a1d4667b8"
SRC_URI[go_1_5.sha256sum] = "754e06dab1c31ab168fc9db9e32596734015ea9e24bc44cae7f237f417ce4efe"

SRC_URI += "\
        file://Fix-ccache-compilation-issue.patch \
        file://fix-cross-compilation.patch \
        "
