# caros-service

The caros-service class is designed to generate systemd unit files that instance
caros-apps using appctl.

## Usage

The `unimux` recipe serves as an example for the usage of the `caros-service` class:

```
DESCRIPTION = "Universal API Multiplexer"
SECTION = "net"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad"

PR = "r1"

inherit caros-app-mix caros-service
include urilock_${PN}_${PV}.inc

CAROS_APP_SERVICE_${PN} = "unimux.service"

APP_PREFIX = "/usr/caros-apps"
SYSCONFIG_PREFIX = "${sysconfdir}"

SRC_URI += "git://github.com/carosio/unimux.git;protocol=git"

SRCREV = "b70705df963a94539ad34abe52f58359c0767c9c"

S = "${WORKDIR}/git"
```

As seen in the `unimux` recipe `CAROS_APP_SERVICE_${PN}` needs to be set.
It holds the unit file name.

## Variables

These are the variables and there default values used by the caros-service class
to generate and install a systemd unit.

| Variables                | Usage                                           | Default Value                         |
|:-------------------------|:------------------------------------------------|:--------------------------------------|
| `CAROS_APP_SERVICE_${PN}`| unit file name                                  | `" "`                                 |
| `SYSTEMD_UNIT_NAME`      | `${CAROS_APP_SERVICE_${PN}}` without ".service" | -                                     |

The `SYSTEMD_UNIT_NAME` variable is a class internal variable to handle the
installation process of the generated unit file.
