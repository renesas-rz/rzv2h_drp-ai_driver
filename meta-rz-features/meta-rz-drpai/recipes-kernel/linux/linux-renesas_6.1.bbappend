#
# Copyright (C) 2026 Renesas Electronics, Co, Ltd.
#
DESCRIPTION = "DRP-AI Driver with kernel"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/:"

SRC_URI:append:rzv2n-family = "\
    file://0001-add-drpai-property-to-devicetree-RZV2N.patch \
    file://0002-set-cru-amnaxiattr-axilen-RZV2N.patch \
    file://0003-config-macro-eMMC_SD_SEL-to-support-eMMC-boot-RZV2N.patch \
"

# Remove a cpg patch provided by the codec or the opencva recipe
SRC_URI:remove:rzv2h-family = "file://0002-clk-renesas-r9a09g057-cpg-init-DRP-reset-RZV2H.patch"

SRC_URI:append:rzv2h-family = "\
    file://0001-add-drpai-property-to-devicetree-RZV2H.patch \
    file://0002-set-cru-amnaxiattr-axilen-RZV2H.patch \
    file://0003-add-drp-drpai-reset-to-clk-renesas-r9a09g057-cpg-RZV2H.patch \
"

SRC_URI:append:smarc-rzv2l = "\
	file://0001-add-drpai-property-to-devicetree-RZV2L.patch \
	file://0002-ov5645-Add-VGA-720x480-and-720p-resolutions-RZV2L.patch  \
	file://0003-ov5645-Add-pixel-rate-support-for-each-mode-RZV2L.patch \
	file://0004-rzg2l-csi-Add-BGR24-YUYV-and-UYVY-format-support-RZV2L.patch \
	file://0005-rzg2l-ip-Add-BGR24-YUYV-and-UYVY-format-support-RZV2L.patch \
	file://0006-update-ov5645-VGA-setting-RZV2L.patch \
	file://0007-WA-available-v4l2-memory-userptr-RZV2L.patch \
"
