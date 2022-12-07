SUMMARY = "The software for DEMO platform of AGL IVI profile"
DESCRIPTION = "A set of packages belong to AGL Demo Platform"

LICENSE = "MIT"

inherit packagegroup

PROVIDES = "${PACKAGES}"
PACKAGES = "\
    packagegroup-agl-demo-platform-html5 \
    "

RDEPENDS:${PN} += "\
    packagegroup-agl-image-ivi \
    packagegroup-agl-profile-graphical-html5 \
    packagegroup-agl-demo \
    "

# TODO(jdapena): replace this with HTML5 apps.
AGL_APPS = " \
    html5-dashboard \
    html5-launcher \
    html5-homescreen \
    html5-hvac \
    html5-mediaplayer \
    html5-mixer \
    html5-settings \
    html5-aquarium \
    html5-youtube \
    html5-jitsi \
    html5-examples \
    "

RDEPENDS:${PN}:append = " \
    weston-ini-conf-landscape \
    ${@bb.utils.contains('DISTRO_FEATURES', 'agl-devel', 'unzip' , '', d)} \
    qtquickcontrols2-agl \
    qtquickcontrols2-agl-style \
    ${AGL_APPS} \
    "
