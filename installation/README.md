# Installation

RZ/V2H DRP-AI Driver can be added even after RZ/V2H Linux build environment is built.  
Please follow the steps below to add the RZ/V2H DRP-AI Driver to your RZ/V2H Linux build environment. 

#### 1. Store the downloaded RZ/V2H DRP-AI Driver (eg rzv2h_drp-ai_driver-main.zip) in the $WORK* directory.   
*: $WORK is the Linux build directory.  

#### 2. Use the following command to deploy the DRP-AI Driver in the RZ/V2H Linux environment.
~~~  
$ unzip rzv2h_drp-ai_driver-main.zip  
$ mkdir -p meta-rz-features
$ mv rzv2h_drp-ai_driver-main/meta-rz-features/meta-rz-drpai/ ./meta-rz-features/
$ TEMPLATECONF=${PWD}/meta-renesas/meta-rzv2h/docs/template/conf/ \
    source poky/oe-init-build-env
$ bitbake-layers add-layer ../meta-rz-features/meta-rz-drpai
~~~

#### 3. Rebuild the SD image with the following command.
~~~
$ MACHINE=rzv2h-evk-ver1 bitbake linux-renesas -c compile -f
$ MACHINE=rzv2h-evk-ver1 bitbake linux-renesas -c deploy
$ MACHINE=rzv2h-evk-ver1 bitbake core-image-weston -c cleanall  
$ MACHINE=rzv2h-evk-ver1 bitbake core-image-weston  
~~~  
When the rebuild is successfully completed, the following file is generated.  
$WORK/build/tmp/deploy/images/rzv2h-evk-ver1  
* core-image-weston-rzv2h-evk-ver1.wic.bmap  
* core-image-weston-rzv2h-evk-ver1.wic.gz  

#### 4. Generate the Cross Compiler with the following command.  
~~~  
$ MACHINE=rzv2h-evk-ver1 bitbake core-image-weston -c populate_sdk  
~~~  
When the above command is successfully completed, the following is generated.  
$WORK/build/tmp/deploy/sdk  
* poky-glibc-x86_64-core-image-weston-aarch64-rzv2h-evk-ver1-toolchain-3.1.26.sh  

