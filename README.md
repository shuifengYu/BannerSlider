BannerSlider

BannerSlider主要用来滑动显示图片集，比如banner图，应用介绍页等，目前做的是带自动滑动功能的，后期会做成可配置选项，当然现在已经支持一些很简单的配置，比如每张图片停留之间，indicate小点的图片，图片加载默认图等

还有许多需要自定义的地方可以之下下载源码改就行了，代码很简单。

使用

step 1:添加gradle依赖

    compile 'com.coder_yu.banner-slider:0.0.12'

Step 2:添加权限

    <!-- if you want to load images from the internet -->
    <uses-permission android:name="android.permission.INTERNET" />

    <!-- if you want to load images from a file OR from the internet -->
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />

step 3:使用

    UIConfig uiConfig = new UIConfig.Builder()
                    .imageLoadFailedRes(R.drawable.img_load_failed)
                    .imageLoadingRes(R.drawable.img_loading)
                    .indicatesMarginBottomDP(10)
                    .duration(5000)
                    .indicateSelectedRes(R.drawable.aide_shape_indicate_selected)
                    .indicateUnSelectedRes(R.drawable.aide_shape_indicate_selected)
                    .build();
    BannerFragment fragment = BannerFragment.newInstance(imagesEntityList,uiConfig);
    getSupportFragmentManager().beginTransaction().replace(R.id.main_banner_container, fragment).commit();


![a](example.jpg)