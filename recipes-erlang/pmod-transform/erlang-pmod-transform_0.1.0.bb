SUMMARY = "Parse Transform for Parameter Modules"
SECTION = "devel"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://EPLICENCE;md5=f4e586d08cc73e9c7373939f6806d647"

SRCREV="9e1517dcd04fc7eda52a928709b0a236c8ebe1a3"
PR = "r5"

SRC_URI = "git://github.com/carosio/pmod_transform.git;branch=travelping"

S = "${WORKDIR}/git"

inherit tetrapak

CLEANBROKEN = "1"

python () {
    erlang_def_package("pmod-transform", "pmod_transform*", "ebin", "Makefile EPLICENCE src include tests", d)
}

