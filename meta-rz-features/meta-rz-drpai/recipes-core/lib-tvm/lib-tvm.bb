SUMMARY = "RZ/V2H TVM LIB install into rootfs /usr/lib/"
SECTION = "libs"
LICENSE = "(Apache-2.0)"
LIC_FILES_CHKSUM = "file://${WORKDIR}/lib_binaries/licenses/LICENSE;md5=0ba5044c64ef53cb0189c9546081e228"

SRC_URI = " \
	file://lib_tvm.tar.gz \
"

RDEPENDS:${PN} = "mmngrbuf-user-module mmngr-user-module"
DEPENDS = "mmngr-user-module mmngrbuf-user-module"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

do_install() {
	install -d ${D}${libdir}/
	install -m 0755 ${WORKDIR}/lib_binaries/libtvm_runtime.so* ${D}${libdir}/
	ln -sf libtvm_runtime.so.2.5.1 ${D}${libdir}/libtvm_runtime.so
}

FILES:${PN} = "${libdir}/*.so ${libdir}/*.so.2.5.1"
INSANE_SKIP:${PN} += "dev-so"
INSANE_SKIP:${PN} += "installed-vs-shipped"
INSANE_SKIP:${PN} += "usrmerge"
