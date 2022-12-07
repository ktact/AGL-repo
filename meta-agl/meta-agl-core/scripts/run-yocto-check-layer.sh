#!/bin/bash
set -x

SCRIPTPATH="$( cd $(dirname $0) >/dev/null 2>&1 ; pwd -P )"
echo $SCRIPTPATH
AGLROOT="$SCRIPTPATH/../../.."
POKYDIR="$AGLROOT/external/poky"
TMPROOT=`mktemp -d`

rm -rf ${TMPROOT}/testbuild-ycl || true
mkdir -p ${TMPROOT}/testbuild-ycl
cd ${TMPROOT}/testbuild-ycl

source $POKYDIR/oe-init-build-env .

cat << EOF >> conf/local.conf
# just define defaults
AGL_FEATURES ?= ""
AGL_EXTRA_IMAGE_FSTYPES ?= ""

# important settings imported from poky-agl.conf
# we do not import 
DISTRO_FEATURES:append = " systemd"
DISTRO_FEATURES_BACKFILL_CONSIDERED:append = " sysvinit"
VIRTUAL-RUNTIME_init_manager = "systemd"

EOF


yocto-check-layer \
	--dependency \
	$AGLROOT/external/meta-openembedded/meta-oe \
	-- \
	$AGLROOT/meta-agl/meta-agl-core


[ $? = 0 ] && rm -rf ${TMPROOT}/testbuild-ycl

exit 0

	--dependency \
	    $AGLROOT/external/meta-openembedded/meta-oe \
	    $AGLROOT/external/meta-openembedded/meta-python \
	    $AGLROOT/external/meta-openembedded/meta-networking \
