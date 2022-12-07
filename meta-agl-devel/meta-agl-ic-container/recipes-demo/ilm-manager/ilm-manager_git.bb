SUMMARY = "Tiny window manager for wayland-ivi-extension"
DESCRIPTION = "Tiny window manager for wayland-ivi-extension"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "jsoncpp wayland-ivi-extension"

PV = "0.1.0+rev${SRCPV}"

SRCREV = "e3a33d47195e4656f7117753d27a0f2d6b21aab9"
SRC_URI = " \
    git://github.com/AGLExport/ilm-manager.git;branch=master;protocol=https \
    file://agl.json \
    file://ilm-manager.service \
    "
S = "${WORKDIR}/git"

inherit autotools pkgconfig systemd

do_install:append() {
    #install scripts
    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/agl.json ${D}${sysconfdir}

    install -d ${D}/${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/ilm-manager.service ${D}${systemd_system_unitdir}
}

FILES:${PN} += " ${systemd_system_unitdir} ${sysconfdir} "
SYSTEMD_PACKAGES = "${PN}"
SYSTEMD_SERVICE:${PN} = "ilm-manager.service"
