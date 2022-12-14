SUMMARY = "FUSE implementation of overlayfs."
DESCRIPTION = "An implementation of overlay+shiftfs in FUSE for rootless \
containers."

LICENSE = "GPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"

SRCREV = "098d9ad79fdbb8538adde08628408aa32a8b4b17"
SRC_URI = " \
	git://github.com/containers/fuse-overlayfs.git;nobranch=1;protocol=https \
	file://0001-Fix-buffer-overflow-on-workdir-path.patch \
"

DEPENDS = "fuse3"

S = "${WORKDIR}/git"

inherit autotools pkgconfig
