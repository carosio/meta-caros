# workarround for bug in systemd.class
#
# when a package has only ONE SYSTEMD_PACKAGES, the class will add all units that
# are mentioned in Conflicts to the package. For systemd itself, this will add
# shutdown.target to system-binfmt, which is bogus

# work arround by having system-binfmt in SYSTEMD_PACKAGES twice

SYSTEMD_PACKAGES += "${PN}-binfmt"
