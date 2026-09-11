SUMMARY = "RUHMI LIB install into rootfs /usr/lib/"
SECTION = "libs"
LICENSE = "(Apache-2.0)"
LIC_FILES_CHKSUM = "file://${WORKDIR}/lib_binaries/licenses/LICENSE;md5=0ba5044c64ef53cb0189c9546081e228"

RUHMI_VER = "RUHMI_R2026-06_v2.8.0-hotfix"

SRC_URI = ""
SRC_URI:rzv2n-family = "file://lib_ruhmi-RZV2H-RZV2N.tar.gz"
SRC_URI:rzv2h-family = "file://lib_ruhmi-RZV2H-RZV2N.tar.gz"
SRC_URI:smarc-rzv2l = "file://lib_ruhmi-RZV2L.tar.gz"

RDEPENDS:${PN} = "mmngrbuf-user-module mmngr-user-module"
DEPENDS = "mmngr-user-module mmngrbuf-user-module"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

do_install() {
    install -d ${D}${libdir}/

    for f in ${WORKDIR}/lib_binaries/*.${RUHMI_VER}; do
        install -m 0755 $f ${D}${libdir}/

        fname=$(basename $f)
        linkname=${fname%.${RUHMI_VER}}

        ln -sf $fname ${D}${libdir}/$linkname
    done
}

FILES:${PN} = "${libdir}/*"
INSANE_SKIP:${PN} += "dev-so"
INSANE_SKIP:${PN} += "installed-vs-shipped"
INSANE_SKIP:${PN} += "usrmerge"
INSANE_SKIP:${PN} += "ldflags file-rdeps"

RPROVIDES:${PN} += "\
    libarm_compute.so()(64bit) \
    libarm_compute_core.so()(64bit) \
    libarm_compute_graph.so()(64bit) \
"
