FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

RRECOMMENDS_${PN}_remove = "docker-registry"

PR := "${PR}.3"

