DESCRIPTION = "User space mappable DMA Buffer Driver"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = " \
    file://LICENSE;md5=bebf0492502927bef0741aa04d1f35f5 \
"

inherit module
require include/rzg2-modules-common.inc

DEPENDS = "linux-renesas"
PN = "kernel-module-udmabuf"
PV = "v3.2.4"

SRC_URI = "git://github.com/ikwzm/udmabuf.git;protocol=git"
SRCREV = "24a1346c1f4371ef6161699c8ccb8bf968e6bb0a"

SRC_URI_append = " \
	file://0001-change-makefile.patch \
"

S = "${WORKDIR}/git"

# Build u-dma-buf kernel module without suffix
KERNEL_MODULE_PACKAGE_SUFFIX = ""


do_compile() {
    cd ${S}
    make all
}


do_install () {
    install -d ${D}/lib/modules/${KERNEL_VERSION}/kernel/
    install -m 644 ${S}/u-dma-buf.ko ${D}/lib/modules/${KERNEL_VERSION}/kernel/
}

PACKAGES = " \
    ${PN} \
    ${PN}-dev \
"

FILES_${PN} = " \
    /lib/modules/${KERNEL_VERSION}/kernel/u-dma-buf.ko \
"

#Package name aliases
RPROVIDES_${PN} += "kernel-module-udmabuf"

# Autoload u-dma-buf
KERNEL_MODULE_AUTOLOAD += "u-dma-buf"
