#
# Copyright (C) 2023 RenesasElectronics, Co, Ltd.
#
DESCRIPTION = "DRP-AI Driver with kernel for RZ/V2H"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/:"

SRC_URI_append += "\
	file://0001-enable-u-dma-buf-function.patch \
	file://0002-enable-drpai-drv.patch \
"