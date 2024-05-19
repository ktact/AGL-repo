
PACKAGECONFIG:append:harden = " pam-wheel"
do_install:append:harden () {
    if [ "${@bb.utils.contains('DISABLE_ROOT', 'True', 'yes', 'no', d)}" = "yes" ]; then
        sed -i -e 's:root ALL=(ALL) ALL:#root ALL=(ALL) ALL:' ${D}${sysconfdir}/sudoers
    fi
}
