SUMMARY = "Dump readable YAML and load safely any YAML"
HOMEPAGE = "http://aboutcode.org/"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://apache-2.0.LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

DEPENDS += "\
            python3-pyyaml-native \
            python3-setuptools-scm-native \
            python3-toml-native \
            "

PYPI_PACKAGE = "saneyaml"

SRC_URI[sha256sum] = "d6074f1959041342ab41d74a6f904720ffbcf63c94467858e0e22e17e3c43d41"

inherit pypi
inherit setuptools3

BBCLASSEXTEND = "native"

