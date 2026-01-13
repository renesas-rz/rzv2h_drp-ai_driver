DESCRIPTION = "Memory Manager Kernel module for Renesas RZ/V"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/:"

SRC_URI:append = " \
    file://0009-kernel-module-mmngr-bug-fix.patch \
"
