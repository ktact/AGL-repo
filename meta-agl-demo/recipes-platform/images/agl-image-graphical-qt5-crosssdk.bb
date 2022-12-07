SUMMARY = "Cross SDK of Full AGL Distribution for IVI profile"

DESCRIPTION = "SDK image for full AGL Distribution for IVI profile. \
It includes the full meta-toolchain, plus developement headers and libraries \
to form a standalone cross SDK."

#require agl-image-graphical-qt5.bb
require recipes-platform/images/agl-image-weston.inc


LICENSE = "MIT"

require recipes-platform/images/agl-image-minimal-crosssdk.inc

# Add wayland-scanner to SDK (SPEC-945)
# Use TOOLCHAIN_HOST_TASK instead of adding to the packagegroup
# wayland-scanner is in nativesdk-wayland-dev !
# option: add also nativesdk-qtwayland-tools
TOOLCHAIN_HOST_TASK:append = " nativesdk-wayland nativesdk-wayland-dev"

TOOLCHAIN_HOST_TASK:append = " nativesdk-perl-modules "

# Add qtwaylandscanner to the SDK
TOOLCHAIN_HOST_TASK:append = " nativesdk-qtwayland-tools "


inherit populate_sdk populate_sdk_qt5

# Task do_populate_sdk and do_rootfs can't be exec simultaneously.
# Both exec "createrepo" on the same directory, and so one of them
# can failed (randomly).
addtask do_populate_sdk after do_rootfs
