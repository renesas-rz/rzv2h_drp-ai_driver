FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

SRC_URI:append:smarc-rzv2l = " \
    file://weston_drpai.ini \
"

do_install:append:smarc-rzv2l() {
    # Set the output section with name HDMI-A-1 and mode 1280x720
    cat weston_drpai.ini >> ${D}${sysconfdir}/xdg/weston/weston.ini
}
