# caros-app-mix

The caros-app-mix class is designed to build a caros app using mix.

## Usage

The `unimux` recipe serves as an example for the usage of the `caros-app-mix` class:

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

As seen in the `unimux` recipe a few parameters need to be set.
First of all caros-app-mix needs to be included. In the case of `unimux` the
installation directory is changed to `/usr/caros-apps`. And the configuration
directory to `/etc` witch is the default value of the `sysconfdir` variable set
by bitbake.

## Variables

These are the variables and there default values used by the `caros-app-mix`
class to specify installation and configuration for the applications build.

| Variables                | Usage                                           | Default Value                         |
|:-------------------------|:------------------------------------------------|:--------------------------------------|
| `APPNAME`                | name of the application                         | `${PN}`                               |
| `APPVERSION`             | version of the application                      | `${PV}`                               |
| `REL_NAME`               | name of elixir application                      | `${APPNAME}`                          |
| `REL_VSN`                | version of elixir application                   | `${APPVERSION`                        |
| `APP_PREFIX`             | installation directory                          | `/opt/apps`                           |
| `SYSCONFIG_PREFIX`       | configuration directory                         | `${sysconfdir}/apps`                  |
| `CONFFILE`               | configuration file path for appctl              | `${SYSCONFIG_PREFIX}/${APPNAME}.conf` |


## Generating the urilock

The urilock holds all dependencies from the `mix.lock` file located in the
source repository of an elixir application.

To generate the `urilock` simply execute:

```
./gen.py unimux/mix.lock > urilock_unimux_1.2.10.inc
```

The generated `urilock`-file needs to be placed next to the recipe. Also it needs to be specialy named:

```
urilock_<packagename>_<packageversion>.inc
```

This naming scheme is used to match the `include` line in the recipes, see example recipe above.
