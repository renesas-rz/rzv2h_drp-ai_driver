#
# Copyright (C) 2025 Renesas Electronics, Co, Ltd.
#
DESCRIPTION = "DRP-AI Driver with kernel for RZ/V2H"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/:"

SRC_URI:remove = "file://0003-clk-renesas-r9a09g057-cpg-init-DRP-reset.patch"

SRC_URI:append = "\
	file://0001-add-drpai-property-to-devicetree.patch \
	file://0002-enable-drpai-driver.patch \
	file://0003-add-drp-drpai-reset-to-clk-renesas-r9a09g057-cpg.patch \
	file://0004-set-cru-amnaxiattr-axilen.patch \
"
