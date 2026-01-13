IMAGE_INSTALL:append = " \
	lib-tvm \
	kernel-module-mmngr \
	mmngrbuf-user-module \
	mmngr-user-module \
	libjpeg-turbo-dev \
	opencv \
	opencv-dev \
	sudo \
	python3-modules \
	python3-pip \
	python3-wheel \
	python3-setuptools \
	python3-numpy \
	python3-pandas \
	python3-pillow \
	python3-opencv \
"

TOOLCHAIN_TARGET_TASK:append = " drpai "

update_issue() {
    AI_SDK_VERSION="AI SDK V6.00 (Source Code)"

    case "${MACHINE}" in
    rzv2h-evk)
      AI_SDK_BOARD="RZ/V2H EVK"
      AI_SDK_SOC="RZ/V2H"
      ;;
    rzv2h-dev)
      AI_SDK_BOARD="RZ/V2H DEV"
      AI_SDK_SOC="RZ/V2H"
      ;;
    esac

    echo "BOARD: ${AI_SDK_BOARD}" >> ${IMAGE_ROOTFS}/etc/issue
    echo "LSI: ${AI_SDK_SOC}" >> ${IMAGE_ROOTFS}/etc/issue
    echo "${AI_SDK_VERSION}" >> ${IMAGE_ROOTFS}/etc/issue
}
ROOTFS_POSTPROCESS_COMMAND += "update_issue; "

enable_sudo() {
    echo "weston ALL=(ALL:ALL) NOPASSWD: ALL" > ${IMAGE_ROOTFS}/etc/sudoers.d/weston
}
ROOTFS_POSTPROCESS_COMMAND += "enable_sudo;"
