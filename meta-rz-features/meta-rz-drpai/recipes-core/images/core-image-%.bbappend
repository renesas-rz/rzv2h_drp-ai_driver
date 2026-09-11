IMAGE_INSTALL:append = " \
    lib-ruhmi \
    kernel-module-mmngr \
    kernel-module-mmngrbuf \
    mmngr-user-module \
    mmngrbuf-user-module \
    libjpeg-turbo-dev \
    opencv \
    opencv-dev \
    sudo \
    kernel-module-drpai \
    python3-core \
    python3-modules \
    python3-pip \
    python3-wheel \
    python3-setuptools \
    python3-numpy \
    python3-pandas \
    python3-pillow \
    python3-opencv \
    python3-pybind11 \
"

IMAGE_INSTALL:append:smarc-rzv2l = " \
    kernel-module-udmabuf \
    weston-init \
"

TOOLCHAIN_TARGET_TASK:append = " \
    kernel-module-drpai-dev \
    python3-core \
    python3-modules \
    python3-pip \
    python3-wheel \
    python3-setuptools \
    python3-numpy \
    python3-pandas \
    python3-pillow \
    python3-opencv \
    python3-pybind11 \
"

TOOLCHAIN_HOST_TASK:append = " \
    nativesdk-python3-core \
    nativesdk-python3-modules \
    nativesdk-python3-pip \
    nativesdk-python3-wheel \
    nativesdk-python3-setuptools \
    nativesdk-python3-numpy \
    nativesdk-python3-pybind11 \
"

update_issue() {
    case "${MACHINE}" in
    rzv2n-evk)
      AI_SDK_VERSION="AI SDK V8.00 (Source Code)"
      AI_SDK_BOARD="RZ/V2N EVK"
      AI_SDK_SOC="RZ/V2N"
      ;;
    rzv2h-evk)
      AI_SDK_VERSION="AI SDK V8.00 (Source Code)"
      AI_SDK_BOARD="RZ/V2H EVK"
      AI_SDK_SOC="RZ/V2H"
      ;;
    smarc-rzv2l)
      AI_SDK_VERSION="AI SDK V8.00 (Source Code)"
      AI_SDK_BOARD="RZ/V2L SMARC"
      AI_SDK_SOC="RZ/V2L"
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
