inherit cross

# CCACHE does not work properly with GO
CCACHE = ""

require golang-cross.inc
require golang-${PV}.inc
