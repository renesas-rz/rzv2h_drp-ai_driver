FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI:append:rzv2n-family = " \
    file://0001-add-ether-setting-RZV2N.patch \
"

SRC_URI:append:rzv2h-family = " \
    file://0001-add-ether-setting-RZV2H.patch \
"
