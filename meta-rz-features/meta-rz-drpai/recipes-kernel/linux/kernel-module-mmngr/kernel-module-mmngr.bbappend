DESCRIPTION = "Memory Manager Kernel module for Renesas RZ/V"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/:"

SRC_URI_append = " \
    file://0013-kernel-module-mmngr-bug-fix.patch \
"
