RDEPENDS:${PN} += "${@bb.utils.contains('CLANGSDK', '1', 'nativesdk-clang', '', d)}"
