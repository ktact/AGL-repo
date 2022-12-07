SUMMARY     = "Session / Policy Manager for PipeWire"
HOMEPAGE    = "https://gitlab.freedesktop.org/pipewire/wireplumber"
BUGTRACKER  = "https://gitlab.freedesktop.org/pipewire/wireplumber/issues"
AUTHOR      = "George Kiagiadakis <george.kiagiadakis@collabora.com>"
SECTION     = "multimedia"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;beginline=3;md5=e8ad01a5182f2c1b3a2640e9ea268264"

inherit meson pkgconfig systemd

DEPENDS = "glib-2.0 glib-2.0-native pipewire lua"

SRC_URI = "\
    git://gitlab.freedesktop.org/pipewire/wireplumber.git;protocol=https;branch=master \
"
# v0.4.8
SRCREV = "e14bb72dcc85e2130d0ea96768e5ae3b375a041e"

# patches to be able to compile with lower version of meson that is available in AGL.
SRC_URI += "\
    file://0001-Revert-tests-add-pipewire-env-variables-when-running.patch \
    file://0002-Revert-wp-uninstalled-build-this-script-with-the-mes.patch \
    file://0003-spa-json-fix-va_list-APIs-for-different-architecture.patch \
    file://0004-policy-endpoint-device-Fix-endpoints-not-connected-w.patch \
"

PV = "0.4.8"
S  = "${WORKDIR}/git"

WPAPI="0.4"

# use shared lua from the system instead of the static bundled one
EXTRA_OEMESON += "-Dsystem-lua=true"

# introspection in practice is only used for generating API docs
# API docs are available on the website and we don't need to build them
# (plus they depend on hotdoc which is not available here)
EXTRA_OEMESON += "-Dintrospection=disabled -Ddoc=disabled"

PACKAGECONFIG = "\
    ${@bb.utils.filter('DISTRO_FEATURES', 'systemd', d)} \
"

PACKAGECONFIG[systemd] = "-Dsystemd=enabled -Dsystemd-system-service=true -Dsystemd-user-service=false,-Dsystemd=disabled -Dsystemd-system-service=false -Dsystemd-user-service=false,systemd"

do_configure:prepend() {
    # relax meson version requirement
    # we only need 0.54 when building with -Dsystem-lua=false
    sed "s/meson_version : '>= 0.56.0'/meson_version : '>= 0.53.2'/" ${S}/meson.build > ${S}/tmp.build
    mv -f ${S}/tmp.build ${S}/meson.build
}

PACKAGES =+ "\
    lib${PN}-${WPAPI} \
    ${PN}-config \
"

SYSTEMD_SERVICE:${PN} = "wireplumber.service"
FILES:${PN} = "\
    ${bindir}/wireplumber \
    ${bindir}/wpctl \
    ${bindir}/wpexec \
    ${libdir}/wireplumber-${WPAPI}/* \
    ${datadir}/wireplumber/scripts/* \
    ${systemd_system_unitdir}/* \
"
RPROVIDES:${PN} += "virtual/pipewire-sessionmanager"

FILES:lib${PN}-${WPAPI} = "\
    ${libdir}/libwireplumber-${WPAPI}.so.* \
"

FILES:${PN}-config += "\
    ${sysconfdir}/wireplumber/* \
    ${datadir}/wireplumber/*conf \
    ${datadir}/wireplumber/common/* \
    ${datadir}/wireplumber/main.lua.d/* \
    ${datadir}/wireplumber/bluetooth.lua.d/* \
    ${datadir}/wireplumber/policy.lua.d/* \
"
do_install:append() {
    rm -rf ${D}${sysconfdir}/wireplumber/
    rm -f ${D}${datadir}/wireplumber/*conf
    rm -rf ${D}${datadir}/wireplumber/common
    rm -rf ${D}${datadir}/wireplumber/main.lua.d
    rm -rf ${D}${datadir}/wireplumber/bluetooth.lua.d
    rm -rf ${D}${datadir}/wireplumber/policy.lua.d
}
