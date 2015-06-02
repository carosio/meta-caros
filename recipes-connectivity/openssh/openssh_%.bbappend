# this will allow us to "update" from dropbear to openssh:
PR := "${PR}.1"
RREPLACES_${PN}-sshd = "dropbear"
