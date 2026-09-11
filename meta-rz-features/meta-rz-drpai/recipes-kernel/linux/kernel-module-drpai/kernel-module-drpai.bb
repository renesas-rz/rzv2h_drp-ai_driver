SUMMARY = "DRPAI Driver module build recipe file for RZ/V"
DESCRIPTION = "${SUMMARY}"
LICENSE = "GPL-2.0-WITH-Linux-syscall-note"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
NO_GENERIC_LICENSE[GPL-2.0-WITH-Linux-syscall-note] = "COPYING"
COPY_LIC_MANIFEST = "1"
COPY_LIC_DIRS = "1"
LICENSE_CREATE_PACKAGE = "1"

inherit module
require include/rz-modules-common.inc

PN = "kernel-module-drpai"
PV:rzv2h-family = "1.5.0"
PV:rzv2n-family = "1.5.0"
PV:smarc-rzv2l = "2.3.0"

PR = "r0"
S = "${WORKDIR}"

DRPAI_DRV_DIR = ""
DRPAI_DRV_DIR:rzv2h-family = "v2h"
DRPAI_DRV_DIR:rzv2n-family = "v2h"
DRPAI_DRV_DIR:smarc-rzv2l = "v2l"

DEVICE_NAME=""
DEVICE_NAME:rzv2h-family = "RZV2H"
DEVICE_NAME:rzv2n-family = "RZV2N"
DEVICE_NAME:smarc-rzv2l = "RZV2L"

SRC_URI:append:rzv2h-family = "file://${DRPAI_DRV_DIR}/drivers/drpai/Makefile-RZV2H"
SRC_URI:append:rzv2n-family = "file://${DRPAI_DRV_DIR}/drivers/drpai/Makefile-RZV2N"
SRC_URI:append:smarc-rzv2l = "file://${DRPAI_DRV_DIR}/drivers/drpai/Makefile-RZV2L"

SRC_URI:rzv2h-family = " \
    file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46 \
    file://${DRPAI_DRV_DIR}/drivers/drpai/drpai-core.c \
    file://${DRPAI_DRV_DIR}/drivers/drpai/drpai-core.h \
    file://${DRPAI_DRV_DIR}/drivers/drpai/drpai-if.c \
    file://${DRPAI_DRV_DIR}/drivers/drpai/drpai-reg.h \
    file://${DRPAI_DRV_DIR}/drivers/drpai/lock_drpai.c \
    file://${DRPAI_DRV_DIR}/drivers/drpai/lock_drpai.h \
    file://${DRPAI_DRV_DIR}/include/linux/drpai.h \
    file://${DRPAI_DRV_DIR}/include/uapi/linux/drpai.h \
"

SRC_URI:rzv2n-family = " \
    file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46 \
    file://${DRPAI_DRV_DIR}/drivers/drpai/drpai-core.c \
    file://${DRPAI_DRV_DIR}/drivers/drpai/drpai-core.h \
    file://${DRPAI_DRV_DIR}/drivers/drpai/drpai-if.c \
    file://${DRPAI_DRV_DIR}/drivers/drpai/drpai-reg.h \
    file://${DRPAI_DRV_DIR}/drivers/drpai/lock_drpai.c \
    file://${DRPAI_DRV_DIR}/drivers/drpai/lock_drpai.h \
    file://${DRPAI_DRV_DIR}/include/linux/drpai.h \
    file://${DRPAI_DRV_DIR}/include/uapi/linux/drpai.h \
"

SRC_URI:smarc-rzv2l = " \
    file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46 \
    file://${DRPAI_DRV_DIR}/drivers/drpai/drpai-core.c \
    file://${DRPAI_DRV_DIR}/drivers/drpai/drpai-core.h \
    file://${DRPAI_DRV_DIR}/drivers/drpai/drpai-if.c \
    file://${DRPAI_DRV_DIR}/drivers/drpai/drpai-reg.h \
    file://${DRPAI_DRV_DIR}/include/linux/drpai.h \
    file://${DRPAI_DRV_DIR}/include/uapi/linux/drpai.h \
"

# Build Memory Manager kernel module without suffix
KERNEL_MODULE_PACKAGE_SUFFIX = ""

do_compile() {
    install -d ${KERNELSRC}/drivers/drpai
    install -m 0644 ${S}/${DRPAI_DRV_DIR}/drivers/drpai/Makefile-${DEVICE_NAME}  ${S}/${DRPAI_DRV_DIR}/drivers/drpai/Makefile

    install -d ${KERNELSRC}/include/linux
    install -m 0644 ${S}/${DRPAI_DRV_DIR}/include/linux/drpai.h  ${KERNELSRC}/include/linux
    install -d ${KERNELSRC}/include/uapi/linux
    install -m 0644 ${S}/${DRPAI_DRV_DIR}/include/uapi/linux/drpai.h ${KERNELSRC}/include/uapi/linux

    oe_runmake -C ${S}/${DRPAI_DRV_DIR}/drivers/drpai
}

do_install() {
    # Create destination directories
    install -d ${D}/${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/
    install -d ${D}/${includedir}/linux/

    # Install kernel module
    install -m 644 ${S}/${DRPAI_DRV_DIR}/drivers/drpai/drpai.ko ${D}/${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/

    # Install header file for Cross Compiler
    install -m 0644 ${S}/${DRPAI_DRV_DIR}/include/uapi/linux/drpai.h ${D}/${includedir}/linux/
}

PACKAGES = " \
    ${PN} \
    ${PN}-dev \
    ${PN}-dbg \
"

# The list of directories or files that are placed in packages.
FILES:${PN} = " \
    ${nonarch_base_libdir}/modules/${KERNEL_VERSION}/extra/drpai.ko \
"

FILES:${PN}-dev += " \
    ${includedir}/linux/drpai.h \
"

RPROVIDES:${PN} += "kernel-module-drpai"

KERNEL_MODULE_AUTOLOAD = "drpai"

INSANE_SKIP:${PN}-dbg = "buildpaths"
