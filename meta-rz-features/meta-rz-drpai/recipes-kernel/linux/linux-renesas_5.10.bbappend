#
# Copyright (C) 2024 RenesasElectronics, Co, Ltd.
#
DESCRIPTION = "DRP-AI Driver with kernel for RZ/V2H"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/:"

SRC_URI_append += "\
	file://0001-enable-u-dma-buf-function.patch \
	file://0002-add-drpai-prop-to-devicetree.patch \
	file://0003-add-drpai-drv.patch \
"
