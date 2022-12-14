SUMMARY     = "AGL QtQuickControls2 style customizations"
HOMEPAGE    = "https://git.automotivelinux.org/src/qtquickcontrols2-agl-style"
LICENSE     = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=815ca599c9df247a0c7f619bab123dad"

DEPENDS = "qtquickcontrols2"

PV = "1.0+git${SRCPV}"

SRC_URI = "git://gerrit.automotivelinux.org/gerrit/src/qtquickcontrols2-agl-style;protocol=https;branch=${AGL_BRANCH}"
SRCREV = "c02692a3c20d9aed1192137a67d5be882c60e71f"

S = "${WORKDIR}/git"

inherit qmake5

FILES:${PN} += "${OE_QMAKE_PATH_QML}/QtQuick/Controls.2/AGL/*"

RDEPENDS:${PN} += " \
    qtquickcontrols-qmlplugins \
    qtquickcontrols2-qmlplugins \
    qtsvg-plugins \
"
