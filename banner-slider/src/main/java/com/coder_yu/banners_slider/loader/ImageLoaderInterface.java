package com.coder_yu.banners_slider.loader;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by yushuifeng on 2017/7/28.
 */

public interface ImageLoaderInterface extends Serializable {
    @NonNull
    ImageView createImageView(Context context);

    void displayImage(ImageView imageView, Uri imagePath);
}
