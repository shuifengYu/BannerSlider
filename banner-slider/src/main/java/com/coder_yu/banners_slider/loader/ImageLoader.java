package com.coder_yu.banners_slider.loader;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by yushuifeng on 2017/7/28.
 */

public abstract class ImageLoader implements ImageLoaderInterface {
    @Override
    public ImageView createImageView(Context context) {
        ImageView imageview = new ImageView(context);
        imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageview;
    }
}
